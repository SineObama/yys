package com.sine.yys.simulation.model.buff.debuff;

import com.sine.yys.simulation.model.buff.Debuff;
import com.sine.yys.simulation.model.buff.UniqueIBuff;

/**
 * 行动控制debuff。
 * 如魅妖、眩晕、冰冻等使对象无法自由行动的效果。
 */
public interface ControlBuff extends Debuff, UniqueIBuff {
}
