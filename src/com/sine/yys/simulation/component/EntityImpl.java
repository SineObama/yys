package com.sine.yys.simulation.component;

import com.sine.yys.buff.debuff.ControlBuff;
import com.sine.yys.buff.debuff.HunLuan;
import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.event.FinishActionEvent;
import com.sine.yys.event.UseFireEvent;
import com.sine.yys.inter.*;
import com.sine.yys.skill.commonattack.CommonAttack;
import com.sine.yys.skill.operation.OperationImpl;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 战场中的实体，保存了式神信息{@link Shikigami}、属性信息{@link Property}、御魂信息{@link Mitama}，和战斗中的状态（技能cd和buff、事件）。
 */
public class EntityImpl extends SimpleObject implements Entity {
    final EventControllerImpl eventController = new EventControllerImpl();
    final BuffControllerImpl buffController = new BuffControllerImpl();
    final Shikigami shikigami;
    final List<Mitama> mitamas;
    private final Property property;
    private final Map<Object, Object> map = new HashMap<>(3);  // 分别保存技能属性，包括技能cd
    // XXXX 两者的设置由谁负责比较好？
    Camp camp = null;
    FireRepo fireRepo;
    private int life;

    EntityImpl(Property property, Mitama mitama, Shikigami shikigami, String name) {
        super(name, 9999);
        this.property = property;
        this.shikigami = shikigami;
        this.mitamas = new ArrayList<>();
        if (mitama != null)
            this.mitamas.add(mitama);
        this.life = getMaxLife();
    }

    @Override
    protected final void doInit() {
        final Controller controller = getController();
        for (Skill skill : this.shikigami.getSkills()) {
            skill.init(controller, this);
        }
        for (Mitama mitama : this.mitamas) {
            mitama.init(controller, this);
        }
    }

    /**
     * 式神自身的行动逻辑。
     */
    void action() {
        final Operation operation;
        // 判断是否有行动控制debuff，进行相关操作。
        final List<ControlBuff> controlBuffs = this.buffController.getControlBuffs();
        if (controlBuffs.isEmpty()) {  // 无行动控制debuff
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
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(this.getCamp(), this);
                if (targets != null)
                    map.put(activeSkill, targets);
            }

            if (!map.isEmpty())
                operation = this.shikigami.getAI().handle(this, this.camp, map);
            else
                operation = new OperationImpl(null, null);

        } else {  // 受行动控制debuff影响

            ControlBuff controlBuff = controlBuffs.get(0);
            log.info(Msg.info(this, "受行动控制debuff", controlBuff.getName(), "影响"));
            if (controlBuff instanceof HunLuan) {  // 混乱，使用普通攻击，随机攻击一个目标
                final List<Entity> allAlive = new ArrayList<>();
                allAlive.addAll(this.camp.getAllAlive());
                allAlive.addAll(this.camp.getOpposite().getAllAlive());
                allAlive.remove(this);
                operation = new OperationImpl(RandUtil.choose(allAlive), this.getCommonAttack());
            } else {
                operation = new OperationImpl(null, null);
            }

        }

        // 执行操作
        ActiveSkill activeSkill = operation.getSkill();
        if (activeSkill != null) {
            Entity target = operation.getTarget();
            log.info(Msg.info(this, "当前鬼火", this.fireRepo.getFire()));
            log.info(Msg.vector(this, target != null ? "对" : "", target, "使用了", activeSkill.getName()));

            // 消耗鬼火
            int fire = activeSkill.getFire();
            if (fire > 0) {
                UseFireEvent event = new UseFireEvent(this, fire);
                this.camp.getEventController().trigger(event);
                fire = event.getCostFire();
                this.fireRepo.useFire(fire); // XXX 对于荒-月的逻辑修改
                log.info(Msg.info(this, "消耗", fire, "点鬼火，剩余", this.fireRepo.getFire(), "点"));
            }

            // 执行技能
            activeSkill.apply(target);

            this.eventController.trigger(new FinishActionEvent());
        } else {
            log.info(Msg.info(this, "无法行动。"));
        }
    }

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
        return (int) property.getLife();
    }

    @Override
    public int getLostLifeInt() {
        return (int) property.getLife() - life;
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

    /**
     * 不会超过最大值。
     */
    void setLife(int life) {
        if (life > getMaxLife())
            life = getMaxLife();
        this.life = life;
    }

    int addLife(int count) {
        if (count < 0) {
            log.warning("add life by negative value");
            return this.life;
        }
        this.life += count;
        if (this.life > getMaxLife())
            this.life = getMaxLife();
        return this.life;
    }

    int reduceLife(int count) {
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
    public double getFlagDamageCoefficient() {
        return 1.0 + buffController.getFlagDamage();
    }

    @Override
    public boolean mitamaSealed() {
        for (IBuff iBuff : buffController.getMap().values()) {
            if (iBuff instanceof SealMitama)
                return true;
        }
        return false;
    }

    @Override
    public boolean passiveSealed() {
        for (IBuff iBuff : buffController.getMap().values()) {
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

    CommonAttack getCommonAttack() {
        for (Skill skill : shikigami.getSkills()) {
            if (skill instanceof CommonAttack)
                return (CommonAttack) skill;
        }
        // XXX 没有普攻技能
        throw new RuntimeException(getFullName() + " 没有普通攻击。");
    }

    @Override
    public final boolean isDead() {
        return life <= 0;
    }

    public final Camp getCamp() {
        return camp;
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
}
