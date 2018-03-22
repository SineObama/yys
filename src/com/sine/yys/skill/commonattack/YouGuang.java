package com.sine.yys.skill.commonattack;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.PctEffect;

/**
 * 青行灯-幽光。
 */
public class YouGuang extends BaseCommonAttack implements PctEffect {
    @Override
    public String getName() {
        return "幽光";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.2;
    }

    /**
     * 吸火概率
     */
    @Override
    public double getPct() {
        return 0.3;
    }

    @Override
    public void doApply(Entity target) {
        super.doApply(target);
        getController().randomGrab(getSelf(), target, getPct());
    }
}
