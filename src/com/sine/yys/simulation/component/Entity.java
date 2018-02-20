package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.effect.PctEffect;
import com.sine.yys.simulation.component.inter.MitamaController;
import com.sine.yys.simulation.component.shishen.BaseShiShen;
import com.sine.yys.simulation.component.shishen.ShiShen;
import com.sine.yys.simulation.component.mitama.Mitama;
import com.sine.yys.simulation.component.model.BuffController;
import com.sine.yys.simulation.component.model.BuffControllerImpl;
import com.sine.yys.simulation.component.model.EventController;
import com.sine.yys.simulation.component.model.EventControllerImpl;
import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.component.model.buff.debuff.ControlBuff;
import com.sine.yys.simulation.component.model.buff.debuff.HunLuan;
import com.sine.yys.simulation.component.model.event.*;
import com.sine.yys.simulation.component.model.shield.Shield;
import com.sine.yys.simulation.component.skill.Skill;
import com.sine.yys.simulation.component.skill.operation.Operation;
import com.sine.yys.simulation.component.skill.ActiveSkill;
import com.sine.yys.simulation.component.skill.CommonAttack;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.IProperty;
import com.sine.yys.simulation.info.Target;
import com.sine.yys.simulation.rule.CalcDam;
import com.sine.yys.simulation.rule.CalcEffect;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.*;
import java.util.logging.Logger;

/**
 * 战场中的实体，保存了式神信息{@link BaseShiShen}、属性信息{@link IProperty}、御魂信息{@link Mitama}，和战斗中的状态（技能cd和buff、事件）。
 * <p>
 * 以下为旧内容：
 * 实体的基类。
 * 必须含有一个普攻技能{@link CommonAttack}。
 * 调用{@link Entity#init(InitContext)}初始化指定阵营和鬼火仓库。
 * <p>这里实现了程序的主体逻辑，包括行动逻辑，事件的触发等。
 * 技能或御魂通过调用这里的函数以实现自身的逻辑。</p>
 */
public class Entity implements Target, IProperty, MitamaController {
    private final Logger log = Logger.getLogger(getClass().toString());
    private final EventController eventController = new EventControllerImpl();
    private final BuffController buffController = new BuffControllerImpl();

    private final IProperty property;
    private final List<Mitama> mitamas;
    private final ShiShen shiShen;
    private final Map<Class, Map<Object, Object>> map = new HashMap<>(3);  // 分别保存技能属性，包括技能cd

    private int life;
    private double position = 0;

    private Camp camp = null;
    private FireRepo fireRepo;

    public Entity(IProperty property, Mitama mitama, ShiShen shiShen) {
        this.property = property;
        this.shiShen = shiShen;
        this.mitamas = new ArrayList<>();
        this.mitamas.add(mitama);
        this.life = getMaxLife();
    }

    public <T extends Skill, V> V get(Class<T> clazz, Object key, V defaultValue) {
        if (!map.containsKey(clazz))
            map.put(clazz, new HashMap<>());
        Map<Object, Object> map1 = map.get(clazz);
        if (map1.containsKey(key))
            return (V) map1.get(key);
        else
            return defaultValue;
    }

    public <T extends Skill> void put(Class<T> clazz, Object key, Object value) {
        if (!map.containsKey(clazz))
            map.put(clazz, new HashMap<>());
        map.get(clazz).put(key, value);
    }

    final void action() {

        // 推进鬼火行动条
        fireRepo.step();

        // 调用技能step。减少cd
        for (Skill skill : shiShen.getSkills()) {
            skill.step(this);
        }
        clear();  // 重置攻击事件。允许对方彼岸花行动前的伤害触发事件。
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
                int cd = activeSkill.getCD(this);
                if (cd > 0) {
                    log.info(Msg.info(this, "技能 " + activeSkill.getName() + " 还有CD " + cd));
                    continue;
                }
                if (fireRepo.getFire() < activeSkill.getFire())
                    continue;
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(this);
                if (targets != null)
                    map.put(activeSkill, targets);
            }

            if (!map.isEmpty())
                operation = shiShen.getAI().handle(this, map);
            else
                operation = new Operation(null, null);

        } else {  // 受行动控制debuff影响

            ControlBuff controlBuff = controlBuffs.get(0);
            log.info(Msg.info(this, "受行动控制debuff " + controlBuff.getName() + " 影响"));
            if (controlBuff instanceof HunLuan) {  // 混乱，使用普通攻击，随机攻击一个目标
                final List<Entity> allAlive = camp.getAllAlive();
                allAlive.addAll(camp.getOpposite().getAllAlive());
                allAlive.remove(this);
                operation = new Operation(RandUtil.choose(allAlive), getCommonAttack());
            } else {
                operation = new Operation(null, null);
            }

        }
        // 执行操作
        ActiveSkill activeSkill = operation.getSkill();
        if (activeSkill != null) {
            Entity target = operation.getTarget();
            log.info(Msg.info(this, "当前鬼火 " + fireRepo.getFire()));
            log.info(Msg.vector(this, target != null ? "对" : "", target, "使用了 " + activeSkill.getName()));

            // 消耗鬼火
            int fire = activeSkill.getFire();
            if (fire > 0) {
                UseFireEvent event = new UseFireEvent(this, fire);
                camp.getEventController().trigger(event);
                fire = event.getCostFire();
                fireRepo.useFire(fire); // XXX 对于荒-月的逻辑修改
                log.info(Msg.info(this, "消耗 " + fire + " 点鬼火，剩余 " + fireRepo.getFire() + " 点"));
            }

            // 执行技能
            activeSkill.apply(this, target);
        } else {
            log.info(Msg.info(this, "无可用技能，跳过回合"));
        }

        // 重置行动条
        setPosition(0);

        // TODO 行动后事件
        buffController.step(this);
    }


    public final String getFullName() {
        return camp.getFullName() + getName();
    }


    public final double getAttack() {
        return property.getAttack() * (1 + buffController.getAtkPct());
    }


    public final int getMaxLife() {
        return (int) property.getLife();
    }


    public final double getDefense() {
        return property.getDefense() * (1 + buffController.getDefPct());
    }


    public final double getSpeed() {
        return property.getSpeed() + buffController.getSpeed();
    }

    // XXX 可能的负值问题。

    public final double getCritical() {
        return property.getCritical() + buffController.getCritical();
    }


    public final double getCriticalDamage() {
        return property.getCriticalDamage() + buffController.getCriticalDamage();
    }

    // XXX 可能的负值问题。

    public final double getEffectHit() {
        return property.getEffectHit() + buffController.getEffectHit();
    }

    // XXX 可能的负值问题。

    public final double getEffectDef() {
        return property.getEffectDef() + buffController.getEffectDef();
    }


    public final double getLife() {
        return (double) life / getMaxLife();
    }


    private void setLife(int life) {
        this.life = life;
    }


    public final EventController getEventController() {
        return this.eventController;
    }

    public List<ActiveSkill> getActiveSkills() {
        List<ActiveSkill> activeSkills = new ArrayList<>();
        for (Skill skill : shiShen.getSkills()) {
            if (skill instanceof ActiveSkill) {
                activeSkills.add((ActiveSkill) skill);
            }
        }
        return activeSkills;
    }

    /**
     * 添加护盾时计算护盾的数值（厚度）。计算暴击。
     *
     * @param src 原本数值。
     * @return 最终数值。
     */
    public int shieldValue(double src) {
        if (!RandUtil.success(getCritical()))
            return (int) src;
        log.info(Msg.info(this, "暴击"));
        return (int) (src * getCriticalDamage());
    }

    public final void init(InitContext context) {
        camp = context.getOwn();
        fireRepo = context.getFireRepo();
        context.setSelf(this);
        for (Skill skill : shiShen.getSkills()) {
            skill.init(context);
        }
        for (Mitama mitama : mitamas) {
            mitama.init(context);
        }
    }

    private CommonAttack getCommonAttack() {
        for (Skill skill : shiShen.getSkills()) {
            if (skill instanceof CommonAttack)
                return (CommonAttack) skill;
        }
        // XXX 没有普攻技能
        throw new RuntimeException(getFullName() + " 没有普通攻击。");
    }


    public final boolean isDead() {
        return life <= 0;
    }


    public final Camp getCamp() {
        return camp;
    }


    public final BuffController getBuffController() {
        return buffController;
    }


    public final FireRepo getFireRepo() {
        return fireRepo;
    }


    public double getPosition() {
        return position;
    }


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

    public void attack(Entity target, AttackInfo attackInfo) {
        if (target.isDead())  // XXX 只是有时会出现目标已死。有更好的逻辑？
            return;

        eventController.trigger(new AttackEvent(this, target));

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

    public void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife) {
        if (target.isDead())
            return;

        eventController.trigger(new AttackEvent(this, target));

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


    private int getLifeInt() {
        return life;
    }

    private void doDamage(Entity target, int damage) {
        log.info(Msg.damage(this, target, damage));
        if (target.getLifeInt() > damage) {
            double src = target.getLife();
            target.setLife(target.getLifeInt() - damage);
            double dst = target.getLife();
            target.getEventController().trigger(new BeDamageEvent(src, dst));
        } else {
            log.info(Msg.vector(this, "击杀", target, ""));
            target.setLife(0);
            target.getCamp().getPosition(target).setDead(true);
        }
        // FIXME 死后添加debuff会有问题？
        eventController.trigger(new DamageEvent(this, target));
    }


    public void randomGrab(double pct, Entity target) {
        if (RandUtil.success(pct)) {
            int num = target.getFireRepo().grabFire(1);
            if (num > 0)
                log.info(Msg.vector(this, "吸取", target, num + " 点鬼火"));
            fireRepo.addFire(num);
        }
    }


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


    public void xieZhan(Entity target) {
        // 目标死亡则随机攻击另一个目标
        Camp enemy = camp.getOpposite();
        if (!enemy.contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "不在", this, "敌对阵营中，随机协战"));
            target = enemy.randomTarget();
        }
        if (target != null)
            getCommonAttack().xieZhan(this, target);
    }


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


    public final String getName() {
        return shiShen.getName();
    }
}
