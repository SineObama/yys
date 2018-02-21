package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.PctEffect;

public interface Controller {

    void attack(Entity target, AttackInfo attackInfo);

    void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife);

    void randomGrab(double pct, Entity target);

    void applyDebuff(PctEffect effect, Entity target, Debuff debuff);

    void xieZhan(Entity target);

    void clear();
}
