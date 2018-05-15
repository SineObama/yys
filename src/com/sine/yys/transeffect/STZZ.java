package com.sine.yys.transeffect;

import com.sine.yys.info.AttackTypeEnum;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TransferrableEffect;

/**
 * 霜天之织效果。
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

    public Entity getSelf() {
        return zhenNv ? null : self;
    }

    @Override
    public TransferrableEffect<STZZ> through(AttackTypeEnum typeEnum) {
        if (typeEnum == AttackTypeEnum.ZHEN_NV) {
            return new STZZ(self, false, true);
        }
        if (this.transferred)
            return null;
        return new STZZ(this.self, true, false);
    }
}
