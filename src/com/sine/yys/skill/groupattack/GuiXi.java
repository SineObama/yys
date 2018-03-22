package com.sine.yys.skill.groupattack;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.Entity;

/**
 * 般若-鬼袭。
 */
public class GuiXi extends SimpleGroupAttack {
    @Override
    public void doApply(Entity unused) {
        super.doApply(unused);
        getSelf().getBuffController().add(new KuangBao(getLast(), getSelf()));
    }

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

    public int getLast() {
        return 2;
    }

    /**
     * 般若的狂暴状态，隐藏buff。
     */
    public class KuangBao extends BaseIBuff {
        KuangBao(int last, Entity src) {
            super(last, "狂暴状态", src);
        }
    }
}
