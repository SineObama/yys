package com.sine.yys.buff;

import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 一般的buff。
 * 有持续回合数，在回合结束时减1。
 */
public abstract class BaseCommonIBuff extends BaseIBuff implements IBuff {
    private int last;

    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     */
    public BaseCommonIBuff(int last, String name) {
        super(name);
        if (last <= 0)
            throw new RuntimeException("buff持续回合不为正。");
        this.last = last;
    }

    @Override
    public final int getLast() {
        return last;
    }

    @Override
    protected final int doBeforeAction(DamageController controller, Entity self) {
        return last;
    }

    @Override
    protected final int doAfterAction(DamageController controller, Entity self) {
        if (last > 0)
            last -= 1;
        afterStep();
        return last;
    }

    /**
     * 用于一般buff消失后，可能有回调的需求。
     */
    protected void afterStep() {
    }
}
