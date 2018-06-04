package com.sine.yys.transeffect;

import com.sine.yys.info.TransferType;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TransferrableEffect;

/**
 * 霜天之织效果。
 * <p>
 * 为了实现：
 * 1. 该效果可以由草人传递给目标，但不能再从目标身上的涓流传递出去，在效果中设计状态标记。
 * 2. 直接的针女伤害不能触发该效果，但经由草人传递后的伤害可以触发。
 * 3. XXX 经由涓流传递的效果未知。
 */
public class STZZ implements TransferrableEffect<STZZ> {
    private boolean transferred = false;
    private boolean zhenNv = false;
    private Entity self;

    public STZZ(Entity self) {
        this.self = self;
    }

    private STZZ(Entity self, boolean transferred) {
        this.self = self;
        this.transferred = transferred;
    }

    private STZZ(Entity self, boolean transferred, boolean zhenNv) {
        this.self = self;
        this.transferred = transferred;
        this.zhenNv = zhenNv;
    }

    public boolean effective() {
        return !zhenNv;
    }

    public Entity getSelf() {
        return self;
    }

    @Override
    public TransferrableEffect<STZZ> through(TransferType typeEnum) {
        if (typeEnum == TransferType.ZHEN_NV) {
            return new STZZ(self, false, true);
        }
        if (this.transferred)
            return null;
        return new STZZ(this.self, true, false);
    }
}
