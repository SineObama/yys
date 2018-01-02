package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.ActionContext;

/**
 * 青行灯-吸魂灯。
 */
public class XiHunDeng extends SimpleGroupAttack {
    @Override
    public int getFire() {
        return 3;
    }

    @Override
    public String getName() {
        return "吸魂灯";
    }

    @Override
    public String getDetail() {
        return "";
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
    public void apply(ActionContext context) {
        context.getOwn().useFire(getFire());
        final int times = context.getEnemy().getAllShikigami().size();
        super.apply(context);
        super.randomGrab(context, getPct(), times);
    }
}
