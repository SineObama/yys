package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.Entity;

/**
 * 沉默。
 * 只能使用普通攻击。
 */
public class ChenMo extends BaseCommonIBuff implements DispellableDebuff, ControlBuff {
    public ChenMo(int last, Entity src) {
        super(last, "沉默", src);
    }
}
