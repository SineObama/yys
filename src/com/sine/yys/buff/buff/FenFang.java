package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseBeforeActionBuff;
import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;

/**
 * 花鸟卷-花鸟相闻-芬芳。
 */
public class FenFang extends BaseBeforeActionBuff implements DispellableBuff {
    final double secondPct, thirdPct;

    public FenFang(Entity src, double secondPct, double thirdPct) {
        super(2, "芬芳", src);
        this.secondPct = secondPct;
        this.thirdPct = thirdPct;
    }

    @Override
    public void doBeforeAction(DamageController controller, Entity self) {
        if (getLast() == 2) {
            controller.cureByLifePct(getSrc(), self, secondPct);
        } else if (getLast() == 1) {
            controller.cureByLifePct(getSrc(), self, secondPct);
        }
    }
}
