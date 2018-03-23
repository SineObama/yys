package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.Entity;

/**
 * 冰冻。
 */
public class BingDong extends BaseIBuff implements DispellableDebuff, Unmovable {
    public BingDong(int last, Entity src) {
        super(last, "冰冻", src);
    }
}
