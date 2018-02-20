package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.PctEffect;

public class ControllerImpl implements Controller {
    @Override
    public double getPosition() {
        return 0;
    }

    @Override
    public void setPosition(double position) {

    }

    @Override
    public void attack(Entity target, AttackInfo attackInfo) {

    }

    @Override
    public void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife) {

    }

    @Override
    public void randomGrab(double pct, Entity target) {

    }

    @Override
    public void applyDebuff(PctEffect effect, Entity target, Debuff debuff) {

    }

    @Override
    public void xieZhan(Entity target) {

    }

    @Override
    public void clear() {

    }
}
