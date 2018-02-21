package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.buff.buff.KuangBao;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 般若-鬼之假面。
 */
public class GuiZhiJiaMian extends BaseCommonAttack {
    @Override
    public String getName() {
        return "鬼之假面";
    }

    @Override
    public int getTimes(Entity self) {
        return self.getBuffController().getUnique(KuangBao.class) == null ? 1 : 2;  // XXX 般若狂暴平A两段？
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.2;
    }
}
