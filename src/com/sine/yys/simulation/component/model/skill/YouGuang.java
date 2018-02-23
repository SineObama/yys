package com.sine.yys.simulation.component.model.skill;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;

/**
 * 青行灯-幽光。
 */
public class YouGuang extends BaseCommonAttack {
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
    public double getPct() {
        return 0.3;
    }

    @Override
    public void doApply(Controller controller, Entity self, Entity target) {
        super.doApply(controller, self, target);
        controller.randomGrab(getPct(), target);
    }
}
