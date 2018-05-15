package com.sine.yys.inter;

import com.sine.yys.info.AttackTypeEnum;

/**
 * 可传递的效果。
 * <p>
 * 传递时产生新对象。
 * <p>
 * 考虑用于实现霜天之织、击杀判断、不打醒睡眠。
 */
public interface TransferrableEffect<T> {
    TransferrableEffect<T> through(AttackTypeEnum typeEnum);
}
