package com.sine.yys.event;

/**
 * 行动结束事件。
 * <p>
 * 用途：镰鼬专用，只在可行动的回合触发（包括被嘲讽和混乱），{@linkplain com.sine.yys.buff.control.Unmovable 强控}下不触发。
 */
public class FinishActionEvent extends BaseEvent {
}
