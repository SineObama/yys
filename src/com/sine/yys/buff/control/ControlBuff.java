package com.sine.yys.buff.control;

import com.sine.yys.buff.debuff.Debuff;

/**
 * 控制buff。
 * <p>
 * 包括无法行动的效果：变形、眩晕、冰冻、睡眠；
 * 无法自由行动的效果：混乱、嘲讽；
 * 无法使用技能的效果：沉默。
 */
public interface ControlBuff extends Debuff {
}
