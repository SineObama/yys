package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;

/**
 * 青行灯-幽光。
 */
public class YouGuang extends CommonAttack {
    @Override
    public String getName() {
        return "幽光";
    }

    /**
     * 吸火概率
     */
    public double getPct() {
        return 0.3;
    }

    @Override
    public void apply(Controller controller) {
        super.apply(controller);
        controller.randomGrab(getPct(), 1);
    }
}
