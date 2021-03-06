package com.sine.yys.inter;

import com.sine.yys.info.TransferType;

/**
 * 随伤害传递的效果。
 * <p>
 * 传递时调用接口，接受转换信息，进行状态转变，返回新对象。
 * <p>
 * 目前用于实现霜天之织。
 */
public interface TransferrableEffect<T> extends HandlerEffect {
    TransferrableEffect<T> through(TransferType typeEnum);
}
