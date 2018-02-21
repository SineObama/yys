package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.buff.buff.KuangBao;
import com.sine.yys.simulation.model.entity.Entity;

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
    public void doApply(Entity self, Entity unused) {
        super.doApply(self, unused);
        self.getBuffController().addBuff(new KuangBao());
    }
}
