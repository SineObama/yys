package com.sine.yys.simulation.component.core.effect;

import com.sine.yys.simulation.info.Named;

/**
 * 效果。
 * 定义：御魂或技能上某种效果，是相对独立的，可作为事件处理的单元。
 * 比如某技能对生命高于多少就增加伤害、造成伤害时概率混乱目标。
 * （御魂一般只对应一个效果。）
 */
public interface Effect extends Named {
}
