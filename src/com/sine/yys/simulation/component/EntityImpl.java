package com.sine.yys.simulation.component;

import com.sine.yys.info.Property;
import com.sine.yys.inter.*;
import com.sine.yys.skill.commonattack.CommonAttack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 战场中的实体，保存了式神信息{@link Shikigami}、属性信息{@link Property}、御魂信息{@link Mitama}，和战斗中的状态（技能cd和buff、事件）。
 */
public class EntityImpl implements Entity {
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
    private double position = 0;  // 行动位置，范围0-1。

    public EntityImpl(Property property, Mitama mitama, Shikigami shikigami) {
        this.property = property;
        this.shikigami = shikigami;
        this.mitamas = new ArrayList<>();
        if (mitama != null)
            this.mitamas.add(mitama);
        this.life = getMaxLife();
    }

    @Override
    public <T, V> V get(Object key, V defaultValue) {
        if (map.containsKey(key))
            return (V) map.get(key);
        else
            return defaultValue;
    }

    @Override
    public <T> void put(Object key, Object value) {
        map.put(key, value);
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
    public final int getMaxLife() {
        return (int) property.getLife();
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

    // XXX 可能的负值问题。
    void setLife(int life) {
        this.life = life;
    }

    int addLife(int count) {
        this.life += count;
        if (this.life > getMaxLife())
            this.life = getMaxLife();
        return this.life;
    }

    int reduceLife(int count) {
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


    public List<ActiveSkill> getActiveSkills() {
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

    @Override
    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    @Override
    public void addPosition(double count) {
        this.position += count;
        if (this.position > 1.0)
            this.position = 1.0;
    }

    @Override
    public final String getName() {
        return shikigami.getName();
    }
}
