package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.FireRepo;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.info.PctEffect;
import com.sine.yys.simulation.component.model.BuffController;
import com.sine.yys.simulation.component.model.EventController;
import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.IProperty;
import com.sine.yys.simulation.info.Target;

public interface Entity extends Target, IProperty {
    <T, V> V get(Class<T> clazz, Object key, V defaultValue);

    <T> void put(Class<T> clazz, Object key, Object value);

    int getMaxLife();

    EventController getEventController();

    int shieldValue(double src);

    void init(InitContext context);

    boolean isDead();

    Camp getCamp();

    BuffController getBuffController();

    FireRepo getFireRepo();
}
