package com.sine.yys.entity;

import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.buff.debuff.control.*;
import com.sine.yys.event.*;
import com.sine.yys.impl.BuffControllerImpl;
import com.sine.yys.impl.EventControllerImpl;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.inter.base.Mitama;
import com.sine.yys.inter.base.Property;
import com.sine.yys.inter.base.Skill;
import com.sine.yys.shikigami.operation.OperationImpl;
import com.sine.yys.skill.BaseAttackSkill;
import com.sine.yys.util.JSON;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 战场中的实体。
 * <p>
 * 保存了{@linkplain Shikigami 式神信息}、{@linkplain Property 属性}、{@linkplain Mitama 御魂}，
 * 和战斗中的状态（技能cd、{@linkplain IBuff buff}、事件）。
 */
public class EntityImpl extends SimpleObject implements Self, JSONable {
    final EventControllerImpl eventController = new EventControllerImpl();
    private final BuffControllerImpl buffController = new BuffControllerImpl(this);
    private final Shikigami shikigami;
    private final List<Mitama> mitamas;
    private final Property property;
    private final Map<Object, Object> map = new HashMap<>(3);  // 分别保存技能属性，包括技能cd
    private final double lifeTimes;
    private Camp camp = null;
    private FireRepo fireRepo;
    private int life;

    public EntityImpl(Property property, Mitama mitama, Shikigami shikigami, String name, double lifeTimes) {
        super(name, 9999);
        this.property = property;
        this.shikigami = shikigami;
        this.lifeTimes = lifeTimes;
        this.mitamas = new ArrayList<>();
        if (mitama != null)
            this.mitamas.add(mitama);
        this.life = getMaxLife();
    }

    @Override
    protected final void doInit() {
        eventController.setParent(camp.getEventController());
        final Controller controller = getController();
        for (Skill skill : this.shikigami.getSkills()) {
            if (skill instanceof Component)
                ((Component) skill).init(controller, this, camp);
        }
        for (Mitama mitama : this.mitamas) {
            if (mitama instanceof Component)
                ((Component) mitama).init(controller, this, camp);
        }
    }

    /*
     * 整个行动，包括鬼火处理、技能处理、事件触发、行动后的反击等。
     * 多次行动不会返回。
     */
    @Override
    public void action() {
        for (Skill skill : this.shikigami.getSkills())
            skill.beforeAction();

        // 回合前事件
        // 为了行动前彼岸花的控制效果生效，事件要在buff调用之前。
        this.eventController.trigger(new ZhaoCaiMaoEvent());
        this.eventController.trigger(new BeforeRoundEvent(this));

        getController().afterMovement();

        // 包括执行持续伤害、治疗
        this.buffController.beforeAction(getController());

        if (!this.isDead())
            this.action2();

        // buff回合数-1
        this.buffController.afterAction(getController());

        // 回合后事件
        this.eventController.trigger(new AfterRoundEvent(this));

        for (Skill skill : this.shikigami.getSkills())
            skill.afterAction();
    }

    private void action2() {
        log.info(Msg.info(this, "当前鬼火", this.fireRepo.getFire()));

        final Operation operation;
        // 判断是否有行动控制debuff，进行相关操作。
        final ControlBuff controlBuff = this.buffController.getFirstWithPrior(ControlBuff.class);
        if (controlBuff == null) {  // 无行动控制debuff
            // 获取每个主动技能的可选目标，不添加不可用（无目标），或鬼火不足的技能
            Map<ActiveSkill, List<? extends Entity>> map = new HashMap<>();
            for (ActiveSkill activeSkill : this.getActiveSkills()) {
                int cd = activeSkill.getCD();
                if (cd > 0) {
                    log.info(Msg.info(this, "技能", activeSkill.getName(), "还有CD", cd));
                    continue;
                }
                if (this.fireRepo.getFire() < activeSkill.getFire())
                    continue;
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(this.camp, this);
                if (targets != null)
                    map.put(activeSkill, targets);
            }

            if (!map.isEmpty())
                operation = this.shikigami.getAI().handle(this, this.camp, map);
            else
                operation = new OperationImpl(null, null);

        } else {  // 受行动控制debuff影响

            log.info(Msg.info(this, "受控制效果", controlBuff, "影响"));
            if (controlBuff instanceof HunLuan) {  // 混乱，使用普通攻击，随机攻击一个目标
                final List<Entity> allAlive = new ArrayList<>();
                allAlive.addAll(this.camp.getAllAlive());
                allAlive.addAll(this.camp.getOpposite().getAllAlive());
                allAlive.remove(this);
                operation = new OperationImpl(RandUtil.choose(allAlive), this.getCommonAttack());
            } else if (controlBuff instanceof ChaoFeng) {
                Entity target = controlBuff.getSrc();
                if (target.isDead())
                    target = this.camp.getOpposite().randomTarget();
                operation = new OperationImpl(target, this.getCommonAttack());
            } else if (controlBuff instanceof ChenMo) {
                final CommonAttack activeSkill = this.getCommonAttack();
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(this.camp, this);
                if (targets != null)
                    operation = this.shikigami.getAI().handle(this, this.camp, new HashMap<ActiveSkill, List<? extends Entity>>() {{
                        put(activeSkill, targets);
                    }});
                else
                    operation = new OperationImpl(null, null);
            } else if (controlBuff instanceof Unmovable) {
                operation = new OperationImpl(null, null);
            } else {
                log.warning("没有判断出控制效果。默认无法行动。" + controlBuff);
                operation = new OperationImpl(null, null);
            }

        }

        // 执行操作
        ActiveSkill activeSkill = operation.getSkill();
        if (activeSkill != null) {
            Entity target = operation.getTarget();
            log.info(Msg.vector(this, target != null ? "对" : "", target, "使用了", activeSkill.getName()));

            // 消耗鬼火
            int fire = activeSkill.getFire();
            if (fire > 0) {
                fire = camp.getEventController().trigger(new UseFireEvent(this, fire)).getCostFire();
                fireRepo.useFire(fire); // XXX 对于荒-月的逻辑修改
                log.info(Msg.info(this, "消耗", fire, "点鬼火，剩余", fireRepo.getFire(), "点"));
            }

            // 执行技能
            if (activeSkill instanceof BaseAttackSkill && target instanceof ShikigamiEntity) {
                // 触发对方被单体攻击事件
                target.getEventController().trigger(new BeMonoAttackEvent((ShikigamiEntity) target, this));
            }
            activeSkill.apply(target);
            if (activeSkill instanceof CommonAttack) {
                // 触发普攻事件
                this.eventController.trigger(new CommonAttackEvent(this, target));
            }

            this.eventController.trigger(new FinishActionEvent());
        } else {
            log.info(Msg.info(this, "无法行动。"));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V get(Object key, V defaultValue) {
        if (map.containsKey(key))
            return (V) map.get(key);
        else
            return defaultValue;
    }

    @Override
    public void put(Object key, Object value) {
        map.put(key, value);
    }

    @Override
    public final String getFullName() {
        return camp.getFullName() + getName();
    }

    @Override
    public final double getAttack() {
        return property.getAttack() + shikigami.getOriginAttack() * buffController.getAtkPct();
    }

    @Override
    public final int getMaxLife() {
        return (int) (property.getLife() * lifeTimes);
    }

    @Override
    public int getLostLifeInt() {
        return getMaxLife() - life;
    }

    @Override
    public int getLifeInt() {
        return life;
    }

    @Override
    public final double getDefense() {
        return property.getDefense() * (1 + buffController.getDefPct());
    }

    @Override
    public final double getSpeed() {
        return property.getSpeed() + buffController.getSpeed();
    }

    @Override
    public final double getCritical() {
        return property.getCritical() + buffController.getCritical();
    }

    @Override
    public final double getCriticalDamage() {
        return property.getCriticalDamage() + buffController.getCriticalDamage();
    }

    // XXX 可能的负值问题。
    @Override
    public final double getEffectHit() {
        return property.getEffectHit() + buffController.getEffectHit();
    }

    @Override
    public final double getEffectDef() {
        return property.getEffectDef() + buffController.getEffectDef();
    }

    // XXX 可能的负值问题。
    @Override
    public final double getLife() {
        return (double) life / getMaxLife();
    }

    @Override
    public void setLife(int life) {
        if (life > getMaxLife())
            life = getMaxLife();
        this.life = life;
    }

    @Override
    public int addLife(int count) {
        if (count < 0) {
            log.warning("add life by negative value");
            return this.life;
        }
        log.info(Msg.info(this, "回复生命", count));
        this.life += count;
        if (this.life > getMaxLife())
            this.life = getMaxLife();
        return this.life;
    }

    @Override
    public int reduceLife(int count) {
        if (count < 0) {
            log.warning("reduce life by negative value");
            return this.life;
        }
        this.life -= count;
        if (this.life < 0)
            this.life = 0;
        return this.life;
    }

    @Override
    public final EventController getEventController() {
        return this.eventController;
    }

    @Override
    public double getCureCoefficient() {
        return 1.0 + buffController.getCure();
    }

    @Override
    public double getDamageCoefficient() {
        return 1.0 + buffController.getDamageUp();
    }

    @Override
    public double getBeDamageCoefficient() {
        return 1.0 + buffController.getBeDamage();
    }

    @Override
    public double getFlagDamageCoefficient() {
        return 1.0 + buffController.getFlagDamage();
    }

    @Override
    public boolean mitamaSealed() {
        for (IBuff iBuff : buffController.getAll()) {
            if (iBuff instanceof SealMitama)
                return true;
        }
        return false;
    }

    @Override
    public boolean passiveSealed() {
        for (IBuff iBuff : buffController.getAll()) {
            if (iBuff instanceof SealPassive)
                return true;
        }
        return false;
    }

    private List<ActiveSkill> getActiveSkills() {
        List<ActiveSkill> activeSkills = new ArrayList<>();
        for (Skill skill : shikigami.getSkills()) {
            if (skill instanceof ActiveSkill) {
                activeSkills.add((ActiveSkill) skill);
            }
        }
        return activeSkills;
    }

    @Override
    public CommonAttack getCommonAttack() {
        for (Skill skill : shikigami.getSkills()) {
            if (skill instanceof CommonAttack)
                return (CommonAttack) skill;
        }
        // XXX 没有普攻技能
        throw new RuntimeException(getFullName() + " 没有普通攻击。");
    }

    @Override
    public void xieZhan(Entity target) {
        target = applyControl(target);
        if (target == null)
            return;
        if (!camp.getOpposite().contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "不在", this, "敌方阵营中，随机协战"));
            target = camp.getOpposite().randomTarget();
        }
        if (target != null)
            getCommonAttack().xieZhan(target);
    }

    @Override
    public Entity applyControl(Entity target) {
        final ControlBuff controlBuff = buffController.getFirstWithPrior(ControlBuff.class);
        if (controlBuff instanceof Unmovable)
            return null;
        if (controlBuff instanceof ChaoFeng) {
            final ChaoFeng chaoFeng = (ChaoFeng) controlBuff;
            target = chaoFeng.getSrc();
        }
        if (target.isDead()) {
            log.info(Msg.info(target, "已死，随机攻击"));
            target = camp.getOpposite().randomTarget();
        }
        return target;
    }

    @Override
    public final boolean isDead() {
        return life <= 0;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    @Override
    public final BuffController getBuffController() {
        return buffController;
    }

    @Override
    public final FireRepo getFireRepo() {
        return fireRepo;
    }

    public void setFireRepo(FireRepo fireRepo) {
        this.fireRepo = fireRepo;
    }

    @Override
    public String toJSON() {
        return JSON.format("name", getFullName(), "mitama", mitamas, "life", life);
    }
}
