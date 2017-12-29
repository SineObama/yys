package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.OperationHandler;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.List;

/**
 * 式神实体。
 * 包含属性，状态等。
 */
public interface Entity {
    OperationHandler getAI();
    List<Skill> getSkills();
    String getName();

    int getAttack();

    int getMaxLife();

    int getDefend();

    double getSpeed();

    double getCritical();

    double getCriticalDamage();

    double getEffectHit();

    double getEffectDef();

    int getLife();

    void setLife(int life);

    Shield getShield();

    void setShield(Shield shield);
}
