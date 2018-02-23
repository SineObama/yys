package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.*;
import com.sine.yys.simulation.component.model.mitama.BaseMitama;
import com.sine.yys.simulation.component.model.mitama.Mitama;
import com.sine.yys.simulation.component.model.shishen.BaseShiShen;
import com.sine.yys.simulation.component.model.skill.ActiveSkill;
import com.sine.yys.simulation.component.model.skill.CommonAttack;
import com.sine.yys.simulation.component.model.skill.Skill;
import com.sine.yys.simulation.event.EventController;
import com.sine.yys.simulation.event.EventControllerImpl;
import com.sine.yys.simulation.info.IProperty;
import com.sine.yys.simulation.info.Target;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 战场中的实体，保存了式神信息{@link BaseShiShen}、属性信息{@link IProperty}、御魂信息{@link Mitama}，和战斗中的状态（技能cd和buff、事件）。
 * <p>
 * 以下为旧内容：
 * 实体的基类。
 * 必须含有一个普攻技能{@link CommonAttack}。
 * <p>这里实现了程序的主体逻辑，包括行动逻辑，事件的触发等。
 * 技能或御魂通过调用这里的函数以实现自身的逻辑。</p>
 */
public class BaseEntity implements Target, IProperty, Entity {
    final EventController eventController = new EventControllerImpl();
    final BuffController buffController = new BuffControllerImpl();
    final BaseShiShen shiShen;
    final List<BaseMitama> mitamas;
    private final Logger log = Logger.getLogger(getClass().toString());
    private final IProperty property;
    private final Map<Class, Map<Object, Object>> map = new HashMap<>(3);  // 分别保存技能属性，包括技能cd

    int life;
    double position = 0;

    // XXXX 两者的设置由谁负责比较好？
    Camp camp = null;
    FireRepo fireRepo;

    public BaseEntity(IProperty property, BaseMitama mitama, BaseShiShen shiShen) {
        this.property = property;
        this.shiShen = shiShen;
        this.mitamas = new ArrayList<>();
        this.mitamas.add(mitama);
        this.life = getMaxLife();
    }

    public <T, V> V get(Class<T> clazz, Object key, V defaultValue) {
        if (!map.containsKey(clazz))
            map.put(clazz, new HashMap<>());
        Map<Object, Object> map1 = map.get(clazz);
        if (map1.containsKey(key))
            return (V) map1.get(key);
        else
            return defaultValue;
    }

    public <T> void put(Class<T> clazz, Object key, Object value) {
        if (!map.containsKey(clazz))
            map.put(clazz, new HashMap<>());
        map.get(clazz).put(key, value);
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

    @Override
    public int getLifeInt() {
        return life;
    }

    public final double getDefense() {
        return property.getDefense() * (1 + buffController.getDefPct());
    }

    public final double getSpeed() {
        return property.getSpeed() + buffController.getSpeed();
    }

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

    public final double getEffectDef() {
        return property.getEffectDef() + buffController.getEffectDef();
    }

    // XXX 可能的负值问题。

    public final double getLife() {
        return (double) life / getMaxLife();
    }

    // XXX 可能的负值问题。

    void setLife(int life) {
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

    CommonAttack getCommonAttack() {
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

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public final BuffController getBuffController() {
        return buffController;
    }

    public final FireRepo getFireRepo() {
        return fireRepo;
    }

    public void setFireRepo(FireRepo fireRepo) {
        this.fireRepo = fireRepo;
    }

    public double getPosition() {
        return position;
    }


    public void setPosition(double position) {
        this.position = position;
    }

    @Override
    public final String getName() {
        return shiShen.getName();
    }
}
