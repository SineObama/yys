package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.BuffController;
import com.sine.yys.simulation.component.BuffControllerImpl;
import com.sine.yys.simulation.component.FireRepo;
import com.sine.yys.simulation.component.event.EventController;
import com.sine.yys.simulation.component.event.EventControllerImpl;
import com.sine.yys.simulation.component.operationhandler.AutoOperationHandler;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.AttackInfo;
import com.sine.yys.simulation.model.Property;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Initable;
import com.sine.yys.simulation.model.buff.Debuff;
import com.sine.yys.simulation.model.buff.debuff.ControlBuff;
import com.sine.yys.simulation.model.buff.debuff.HunLuan;
import com.sine.yys.simulation.model.effect.PctEffect;
import com.sine.yys.simulation.model.event.*;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.operation.SimpleOperation;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.CommonAttack;
import com.sine.yys.simulation.model.skill.Skill;
import com.sine.yys.simulation.rule.CalcDam;
import com.sine.yys.simulation.rule.CalcEffect;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.*;
import java.util.logging.Logger;

/**
 * 实体的基类。
 * 必须含有一个普攻技能{@link CommonAttack}。
 * 调用{@link BaseEntity#init(InitContext)}初始化指定阵营和鬼火仓库。
 * <p>这里实现了程序的主体逻辑，包括行动逻辑，事件的触发等。
 * 技能或御魂通过调用这里的函数以实现自身的逻辑。</p>
 */
public abstract class BaseEntity implements Entity, Initable {
    private final Logger log = Logger.getLogger(getClass().toString());
    private final EventController eventController = new EventControllerImpl();
    private final BuffController buffController = new BuffControllerImpl();

    private final Property property;
    private final List<Mitama> mitamas;
    private final List<Skill> skills;
    private final String name;

    private int life;
    private double position;

    private Camp camp;
    private FireRepo fireRepo;

    BaseEntity(Property property, Mitama mitama, List<Skill> skills, String name) {
        this.property = property;
        this.name = name;
        this.mitamas = new ArrayList<>();
        this.mitamas.add(mitama);
        this.skills = new ArrayList<>();
        this.skills.addAll(skills);
        this.life = (int) property.getLife();
        this.position = 0;
    }

    @Override
    public final void action() {

        // 推进鬼火行动条
        fireRepo.step();

        // 行动前事件
        camp.getEventController().trigger(new BeforeActionEvent(this));

        // 行动开始

        final Operation operation;
        // 判断是否有行动控制debuff，进行相关操作。
        final List<ControlBuff> controlBuffs = buffController.getControlBuffs();
        if (controlBuffs.isEmpty()) {  // 无行动控制debuff
            // 获取每个主动技能的可选目标，不添加不可用（无目标），或鬼火不足的技能
            Map<ActiveSkill, List<? extends Entity>> map = new HashMap<>();
            for (ActiveSkill activeSkill : getActiveSkills()) {
                if (fireRepo.getFire() < activeSkill.getFire())
                    continue;
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(this);
                if (targets != null)
                    map.put(activeSkill, targets);
            }

            if (!map.isEmpty())
                operation = getAI().handle(this, map);
            else
                operation = new SimpleOperation(null, null);

        } else {  // 受行动控制debuff影响

            ControlBuff controlBuff = controlBuffs.get(0);
            log.info(Msg.info(this, "受行动控制debuff " + controlBuff.getName() + " 影响"));
            if (controlBuff instanceof HunLuan) {  // 混乱，使用普通攻击，随机攻击一个目标
                final List<Entity> allAlive = camp.getAllAlive();
                allAlive.addAll(camp.getOpposite().getAllAlive());
                allAlive.remove(this);
                operation = new SimpleOperation(RandUtil.choose(allAlive), getCommonAttack());
            } else {
                operation = new SimpleOperation(null, null);
            }

        }
        // 执行操作
        ActiveSkill activeSkill = operation.getSkill();
        if (activeSkill != null) {
            Entity target = operation.getTarget();
            log.info(Msg.vector(this, target != null ? "对" : "", target, "使用了 " + activeSkill.getName()));

            // 消耗鬼火
            int fire = activeSkill.getFire();
            if (fire > 0) {
                UseFireEvent event = new UseFireEvent(this, fire);
                camp.getEventController().trigger(event);
                fire = event.getCostFire();
                fireRepo.useFire(fire); // XXX 对于荒-月的逻辑修改
                log.info(Msg.info(this, "消耗 " + fire + " 点鬼火，当前剩余 " + fireRepo.getFire() + " 点"));
            }

            // 执行技能
            activeSkill.apply(target);
        } else {
            log.info(Msg.info(this, "无可用技能，跳过回合"));
        }

        // 重置行动条
        setPosition(0);

        // TODO 行动后事件
        buffController.step(this);
    }

    @Override
    public final String getFullName() {
        return camp.getFullName() + getName();
    }

    @Override
    public final double getAttack() {
        return property.getAttack() * (1 + buffController.getAtkPct());
    }

    @Override
    public final double getMaxLife() {
        return property.getLife();
    }

    @Override
    public final double getDefense() {
        return property.getDefense() * (1 + buffController.getDefPct());
    }

    @Override
    public final double getSpeed() {
        return property.getSpeed() + buffController.getSpeed();
    }

    // XXX 可能的负值问题。
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

    // XXX 可能的负值问题。
    @Override
    public final double getEffectDef() {
        return property.getEffectDef() + buffController.getEffectDef();
    }

    @Override
    public final int getLife() {
        return life;
    }

    @Override
    public final void setLife(int life) {
        this.life = life;
    }

    @Override
    public final double getLifePct() {
        return life / property.getLife();
    }

    @Override
    public final EventController getEventController() {
        return this.eventController;
    }

    public OperationHandler getAI() {
        return new AutoOperationHandler();
    }

    private List<ActiveSkill> getActiveSkills() {
        List<ActiveSkill> activeSkills = new ArrayList<>();
        for (Skill skill : skills) {
            if (skill instanceof ActiveSkill) {
                activeSkills.add((ActiveSkill) skill);
            }
        }
        return activeSkills;
    }

    @Override
    public final void init(InitContext context) {
        camp = context.getOwn();
        fireRepo = context.getFireRepo();
        context.setSelf(this);
        for (Skill skill : skills) {
            ((Initable) skill).init(context);
        }
        for (Mitama mitama : mitamas) {
            ((Initable) mitama).init(context);
        }
    }

    private CommonAttack getCommonAttack() {
        for (Skill skill : skills) {
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

    @Override
    public final BuffController getBuffController() {
        return buffController;
    }

    @Override
    public final FireRepo getFireRepo() {
        return fireRepo;
    }

    @Override
    public double getPosition() {
        return position;
    }

    @Override
    public void setPosition(double position) {
        this.position = position;
    }

    /**
     * 伤害逻辑：
     * 1. 由攻击、伤害系数、对方防御（忽略防御）计算。
     * 2. 根据双方buff进行增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，添加御魂效果。
     */
    @Override
    public void attack(Entity target0, AttackInfo attackInfo) {
        BaseEntity target = (BaseEntity) target0;
        if (target.isDead())  // XXX 只是有时会出现目标已死。有更好的逻辑？
            return;

        // XXXXX 像这种每次都调用是不是不好、太慢
        target.getCamp().getEventController().triggerOff(new BeAttackEvent());
        target.getEventController().triggerOff(new BeAttackEvent());

        // 1.
        final boolean critical = RandUtil.success(getCritical());
        if (critical)
            log.info(Msg.info(this, "暴击"));
        double damage = CalcDam.expect(this, target, attackInfo, critical);

        //3.
        int remain = breakShield(target, (int) damage);

        // 4.
        if (remain != 0) {
            damage = remain;

            PreDamageEvent event = new PreDamageEvent(this, target);
            eventController.trigger(event);
            damage *= event.getCoefficient();

            doDamage(target, (int) damage);

            if (critical) {
                target.getEventController().trigger(new BeCriticalEvent(target, this));
                eventController.trigger(new CriticalEvent(this, target));
            }
        } else {
            log.info(Msg.noDamage(this, target));
        }
    }

    /**
     * 目前只有针女伤害。
     * 1. 计算伤害。
     * 2. 根据旗帜buff增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，附加效果（似乎有比如山童的眩晕）。
     */
    @Override
    public void realDamage(Entity target0, double maxByAttack, double maxPctByMaxLife) {
        BaseEntity target = (BaseEntity) target0;
        if (target.isDead())
            return;
        // 1.
        final double damage1 = getAttack() * maxByAttack;
        final double damage2 = target.getMaxLife() * maxPctByMaxLife;
        double damage = damage1 < damage2 ? damage1 : damage2;

        // 3.
        int remain = breakShield(target, (int) damage);

        // 4.
        if (remain != 0) {
            doDamage(target, (int) damage);
        } else {
            log.info(Msg.noDamage(this, target));
        }

    }

    /**
     * 把伤害施加到盾上。
     *
     * @return 剩余伤害。
     */
    private int breakShield(Entity target, int damage) {
        Iterator<Shield> iterator = target.getBuffController().getShields().iterator();
        for (; iterator.hasNext(); ) {
            Shield shield = iterator.next();
            damage = shield.doDamage(damage);
            if (damage == -1)
                break;
            target.getBuffController().removeShield(shield);
            log.info(Msg.info(target, shield.getName() + " 被击破"));
        }
        if (damage == -1)
            damage = 0;
        return damage;
    }

    private void doDamage(BaseEntity target, int damage) {
        log.info(Msg.damage(this, target, damage));
        if (target.getLife() > damage) {
            target.setLife(target.getLife() - damage);
        } else {
            log.info(Msg.vector(this, "击杀", target, ""));
            target.setLife(0);
            target.getCamp().getPosition(target).setDead(true);
        }
        // FIXME 死后添加debuff会有问题？
        eventController.trigger(new DamageEvent(this, target));
    }

    @Override
    public void randomGrab(double pct, Entity target) {
        if (RandUtil.success(pct)) {
            int num = target.getFireRepo().grabFire(1);
            if (num > 0)
                log.info(Msg.vector(this, "吸取", target, num + " 点鬼火"));
            fireRepo.addFire(num);
        }
    }

    @Override
    public void applyDebuff(PctEffect effect, Entity target, Debuff debuff) {
        if (RandUtil.success(CalcEffect.pct(effect.getPct(), getEffectHit()))) {
            log.info(Msg.trigger(this, effect));
            if (RandUtil.success(CalcEffect.hitPct(target.getEffectDef()))) {
                log.info(Msg.info(target, "获得负面效果 " + debuff.getName()));
                target.getBuffController().addDebuff(debuff);
            } else {
                log.info(Msg.info(target, "抵抗了负面效果 " + debuff.getName()));
            }
        }
    }

    @Override
    public void xieZhan(Entity target) {
        // 目标死亡则随机攻击另一个目标
        Camp enemy = camp.getOpposite();
        if (!enemy.contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "不在", this, "敌对阵营中，随机协战"));
            target = enemy.randomTarget();
        }
        if (target != null)
            getCommonAttack().xieZhan(target);
    }

    @Override
    public void clear() {
        camp.getEventController().setState(BeAttackEvent.class, true);
        camp.getOpposite().getEventController().setState(BeAttackEvent.class, true);
        for (Entity entity : camp.getAllAlive()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
        for (Entity entity : camp.getOpposite().getAllAlive()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
    }

    @Override
    public final String getName() {
        return name;
    }
}
