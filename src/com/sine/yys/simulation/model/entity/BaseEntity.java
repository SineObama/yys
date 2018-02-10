package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.event.EventController;
import com.sine.yys.simulation.component.event.EventControllerImpl;
import com.sine.yys.simulation.component.operationhandler.AutoOperationHandler;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.CommonAttack;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * 式神实体通用逻辑。
 * 可以创建固定属性的式神。
 */
public abstract class BaseEntity implements Entity {
    private final int attack;
    private final int maxLife;
    private final int defense;
    private final double speed;
    private final double critical;
    private final double criticalDamage;
    private final double effectHit;
    private final double effectDef;

    private int life;
    private Shield shield = null;

    private List<Skill> skills;
    private final Mitama mitama;
    private final EventController eventController = new EventControllerImpl();

    private Camp camp;

    public BaseEntity(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, Mitama mitama) {
        this.attack = attack;
        this.maxLife = maxLife;
        this.defense = defense;
        this.speed = speed;
        this.critical = critical;
        this.criticalDamage = criticalDamage;
        this.effectHit = effectHit;
        this.effectDef = effectDef;

        this.life = maxLife;
        this.mitama = mitama;

        // TODO
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getCritical() {
        return critical;
    }

    @Override
    public double getCriticalDamage() {
        return criticalDamage;
    }

    @Override
    public double getEffectHit() {
        return effectHit;
    }

    @Override
    public double getEffectDef() {
        return effectDef;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public double getLifePct() {
        return (double) life / maxLife;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public Shield getShield() {
        return shield;
    }

    @Override
    public void setShield(Shield shield) {
        this.shield = shield;
    }

    @Override
    public EventController getEventController() {
        return this.eventController;
    }

    @Override
    public OperationHandler getAI() {
        return new AutoOperationHandler();
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public List<ActiveSkill> getActiveSkills() {
        List<ActiveSkill> activeSkills = new ArrayList<>();
        for (Skill skill : skills) {
            if (skill instanceof ActiveSkill) {
                activeSkills.add((ActiveSkill) skill);
            }
        }
        return activeSkills;
    }

    @Override
    public void init(InitContext context) {
        this.setCamp(context.getOwn());
        context.setSelf(this);
        for (Skill skill : getSkills()) {
            skill.init(context);
        }
        if (mitama != null)
            mitama.init(context);
    }

    @Override
    public CommonAttack getCommonAttack() {
        for (Skill skill : getSkills()) {
            if (skill instanceof CommonAttack)
                return (CommonAttack) skill;
        }
        // TODO 异常处理？
        return null;
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }

    @Override
    public Camp getCamp() {
        return camp;
    }

    @Override
    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}
