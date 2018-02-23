package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.BuffController;
import com.sine.yys.simulation.component.model.EventController;
import com.sine.yys.simulation.component.skill.ActiveSkill;
import com.sine.yys.simulation.info.IProperty;
import com.sine.yys.simulation.info.Target;

import java.util.List;

public interface Entity extends Target, IProperty {
    <T, V> V get(Class<T> clazz, Object key, V defaultValue);

    <T> void put(Class<T> clazz, Object key, Object value);

    int getMaxLife();

    int getLifeInt();

    int shieldValue(double src);

    boolean isDead();

    Camp getCamp();

    BuffController getBuffController();

    FireRepo getFireRepo();

    double getPosition();

    void setPosition(double position);

    List<ActiveSkill> getActiveSkills();

    EventController getEventController();
}
