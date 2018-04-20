package com.sine.yys.entity;

import com.sine.yys.buff.control.ChaoFeng;
import com.sine.yys.buff.control.ControlBuff;
import com.sine.yys.buff.control.HunLuan;
import com.sine.yys.buff.control.Unmovable;
import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.event.AfterActionEvent;
import com.sine.yys.impl.BuffControllerImpl;
import com.sine.yys.impl.EmptyFireRepo;
import com.sine.yys.impl.EventControllerImpl;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.inter.base.Property;
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
public abstract class EntityImpl extends SimpleObject implements Entity, JSONable {
    final EventControllerImpl eventController = new EventControllerImpl();
    final BuffControllerImpl buffController = new BuffControllerImpl(this);
    final Property property;
    final int maxLife;
    private final Map<Object, Object> map = new HashMap<>(3);  // 分别保存技能属性，包括技能cd
    Camp camp = null;
    FireRepo fireRepo = new EmptyFireRepo();
    int life;

    protected EntityImpl(Property property, String name, int maxLife) {
        super(name, 9999);
        this.property = property;
        this.maxLife = maxLife;
        this.life = maxLife;
    }

    @Override
    protected void doInit() {
        eventController.setParent(camp.getEventController());
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
    public double getAttack() {
        return property.getAttack() * (1 + buffController.getAtkPct());
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public int getLostLifeInt() {
        return maxLife - life;
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
        return (double) life / maxLife;
    }

    @Override
    public void setLife(int life) {
        if (life > maxLife)
            life = maxLife;
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
        if (this.life > maxLife)
            this.life = maxLife;
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

    public abstract CommonAttack getCommonAttack();

    @Override
    public void xieZhan(Entity target) {
        target = applyControl(target);
        if (target == null)
            return;
        if (target.isDead()) {
            log.info(Msg.info(target, "已死，随机协战"));
            target = this.randomTarget();
        } else if (!camp.getOpposite().contain(target)) {  // 目标不在对方阵营中。可能目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "不在", this, "敌方阵营中，随机协战"));
            target = this.randomTarget();
        }
        if (target != null)
            getCommonAttack().xieZhan(target);
    }

    @Override
    public void counter(Entity target) {
        target = applyControl(target);
        if (target == null)
            return;
        if (target.isDead()) {
            log.info(Msg.info(target, "已死，随机反击"));
            target = this.randomTarget();
        }
        if (target != null)
            this.getCommonAttack().counter(target);
        this.eventController.trigger(new AfterActionEvent(this));
    }

    /**
     * 根据是否混乱，获取选择攻击目标。混乱时可能攻击所有人，包括己方。
     */
    private Entity randomTarget() {
        if (buffController.contain(HunLuan.class)) {
            final List<Entity> allAlive = new ArrayList<>();
            allAlive.addAll(this.camp.getAllAlive());
            allAlive.addAll(this.camp.getOpposite().getAllAlive());
            allAlive.remove(this);
            return RandUtil.choose(allAlive);
        }
        return RandUtil.choose(this.camp.getOpposite().getAllAlive());
    }

    /**
     * 根据当前控制效果（强控或嘲讽），重新确认攻击目标。
     * 不处理目标死亡。
     *
     * @param origin 期望攻击目标。
     * @return 最终攻击目标。无法攻击则为null。
     */
    private Entity applyControl(Entity target) {
        final ControlBuff controlBuff = buffController.getFirstWithPrior(ControlBuff.class);
        if (controlBuff instanceof Unmovable) {
            log.info(Msg.info(this, "无法攻击"));
            return null;
        }
        if (controlBuff instanceof ChaoFeng) {
            final ChaoFeng chaoFeng = (ChaoFeng) controlBuff;
            target = chaoFeng.getSrc();
            log.info(Msg.info(this, "攻击嘲讽目标", target));
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

    @Override
    public String toString() {
        return Msg.join(super.toString(), getPosition(), life);
    }

    @Override
    public String toJSON() {
        return JSON.format("name", getFullName(), "life", life);
    }
}
