package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.inter.Entity;

/**
 * 变形。
 */
public class BianXing extends BaseCommonIBuff implements Unmovable, SealMitama, SealPassive {
    public BianXing(int last, Entity src) {
        super(last, "变形", src);
    }
}
