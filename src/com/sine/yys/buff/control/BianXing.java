package com.sine.yys.buff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.inter.Entity;

/**
 * 变形。
 * 额外封印御魂和被动。
 */
public class BianXing extends BaseIBuff implements Unmovable, SealMitama, SealPassive {
    public BianXing(int last, Entity src) {
        super(last, "变形", src);
    }
}
