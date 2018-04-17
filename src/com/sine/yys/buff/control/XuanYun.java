package com.sine.yys.buff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.Entity;

/**
 * 眩晕。
 * 不可驱散。
 */
public class XuanYun extends BaseIBuff implements Unmovable {
    public XuanYun(Entity src) {
        super(1, "眩晕", src);
    }
}
