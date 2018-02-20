package com.sine.yys.simulation.component.inter;

import com.sine.yys.simulation.component.core.Camp;
import com.sine.yys.simulation.component.core.FireRepo;
import com.sine.yys.simulation.component.core.InitContext;
import com.sine.yys.simulation.info.PctEffect;
import com.sine.yys.simulation.component.core.model.BuffController;
import com.sine.yys.simulation.component.core.model.EventController;
import com.sine.yys.simulation.component.core.model.buff.Debuff;
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

    double getPosition();

    void setPosition(double position);

    void attack(Entity target, AttackInfo attackInfo);

    void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife);

    void randomGrab(double pct, Entity target);

    void applyDebuff(PctEffect effect, Entity target, Debuff debuff);

    void xieZhan(Entity target);

    void clear();
}
