package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseIBuff;

/**
 * 般若的封印御魂和被动效果。
 */
public class FengYin extends BaseIBuff implements SealPassive, SealMitama, DispellableDebuff {
    /**
     * @param last 持续回合数。必须为正。
     */
    public FengYin(int last) {
        super(last, "封印");
    }
}
