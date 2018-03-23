package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.Entity;

/**
 * 弱嘲讽。
 * 可驱散。
 */
public class WeakChaoFeng extends BaseIBuff implements DispellableDebuff, ChaoFeng {
    public WeakChaoFeng(Entity src) {
        super(1, "嘲讽", src);
    }
}
