package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 眩晕。不可驱散。
 */
public class XuanYun extends BaseCommonIBuff implements ControlBuff {
    public XuanYun(Entity src) {
        super(1, "眩晕", src);
    }
}
