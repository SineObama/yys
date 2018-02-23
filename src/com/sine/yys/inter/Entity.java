package com.sine.yys.inter;

import com.sine.yys.info.IProperty;
import com.sine.yys.info.Target;

public interface Entity extends Target, IProperty {
    <T, V> V get(Class<T> clazz, Object key, V defaultValue);

    <T> void put(Class<T> clazz, Object key, Object value);

    int getMaxLife();

    int getLifeInt();

    int shieldValue(double src);

    boolean isDead();

    BuffController getBuffController();

    FireRepo getFireRepo();

    double getPosition();

    void setPosition(double position);

    EventController getEventController();
}
