package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.buff.buff.KuangBao;

/**
 * 般若-鬼袭。
 */
public class GuiXi extends SimpleGroupAttack {
    @Override
    public String getName() {
        return "鬼袭";
    }

    @Override
    public double getCoefficient() {
        return 1.10 * 1.2;
    }

    @Override
    public int getTimes() {
        return 1;
    }

    @Override
    public int getMAXCD() {
        return 2;
    }

    @Override
    public void doApply(Entity unused) {
        super.doApply(unused);
        getSelf().getBuffController().addBuff(new KuangBao());
    }
}
