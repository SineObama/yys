package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.ContextAndRunner;

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
    public void apply(ContextAndRunner context) {
        super.apply(context);
        context.randomGrab(getPct(), 1);
    }
}
