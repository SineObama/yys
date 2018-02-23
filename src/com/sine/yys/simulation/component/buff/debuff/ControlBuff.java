package com.sine.yys.simulation.component.buff.debuff;

import com.sine.yys.simulation.component.buff.Debuff;
import com.sine.yys.simulation.component.buff.UniqueIBuff;

/**
 * 行动控制debuff。
 * 如魅妖、眩晕、冰冻等使对象无法自由行动的效果。
 */
public interface ControlBuff extends Debuff, UniqueIBuff {
}
