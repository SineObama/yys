package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;

/**
 * 青行灯-吸魂灯。
 */
public class XiHunDeng extends SimpleGroupAttack {
    @Override
    public String getName() {
        return "吸魂灯";
    }

    @Override
    public double getCoefficient() {
        return 1.07;
    }

    @Override
    public int getTimes() {
        return 1;
    }

    /**
     * 吸火概率
     */
    public double getPct() {
        return 0.3;
    }

    @Override
    public void apply(Controller controller) {
        final int grabTimes = controller.getEnemy().getAllShikigami().size();
        super.apply(controller);
        controller.randomGrab(getPct(), grabTimes);
    }
}
