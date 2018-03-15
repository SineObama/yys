package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;

/**
 * 持续治疗效果。
 */
public abstract class CureBuff extends BaseCommonIBuff implements DispellableBuff {
    public CureBuff(int last, String prefix, Entity src) {
        super(last, prefix + "-持续治疗", src);
    }

    @Override
    public final void doBeforeAction(DamageController controller, Entity self) {
        controller.cure(getSrc(), self, getCureCount());
    }

    protected abstract double getCureCount();
}
