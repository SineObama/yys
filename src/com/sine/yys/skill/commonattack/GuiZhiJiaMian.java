package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.buff.KuangBao;

/**
 * 般若-鬼之假面。
 */
public class GuiZhiJiaMian extends BaseCommonAttack {
    @Override
    public String getName() {
        return "鬼之假面";
    }

    @Override
    public int getTimes() {
        return getSelf().getBuffController().contain(KuangBao.class) ? 2 : 1;  // XXX 般若狂暴平A两段？
    }

    @Override
    public double getCoefficient() {
        return 1.0 * 1.2;
    }
}
