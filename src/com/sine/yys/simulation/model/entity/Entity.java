package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.event.EventController;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.List;

/**
 * 式神实体。
 * 包含属性，状态等。
 */
public interface Entity extends Target {
    OperationHandler getAI();

    void setSkills(List<Skill> skills);

    List<Skill> getSkills();

    List<ActiveSkill> getActiveSkills();

    String getName();

    int getAttack();

    int getMaxLife();

    int getDefense();

    double getSpeed();

    double getCritical();

    double getCriticalDamage();

    double getEffectHit();

    double getEffectDef();

    int getLife();

    double getLifePct();

    void setLife(int life);

    Shield getShield();

    void setShield(Shield shield);

    EventController getEventController();

    void init(InitContext context);
}
