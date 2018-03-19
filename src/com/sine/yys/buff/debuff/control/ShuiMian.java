package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.Entity;

/**
 * 睡眠。
 */
public class ShuiMian extends BaseCommonIBuff implements DispellableDebuff, Unmovable {
    public ShuiMian(int last, Entity src) {
        super(last, "睡眠", src);
    }
}
