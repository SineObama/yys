package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 般若的封印御魂和被动效果。
 */
public class FengYin extends BaseCommonIBuff implements SealPassive, SealMitama, DispellableDebuff {
    /**
     * @param last 持续回合数。必须为正。
     * @param self
     */
    public FengYin(int last, Entity src) {
        super(last, "封印", src);
    }
}
