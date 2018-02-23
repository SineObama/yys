package com.sine.yys.skill;

import com.sine.yys.buff.buff.KuangBao;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

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
    public void doApply(Controller controller, Entity self, Entity unused) {
        super.doApply(controller, self, unused);
        self.getBuffController().addIBuff(new KuangBao());
    }
}
