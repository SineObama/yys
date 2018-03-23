package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 行动后事件。
 * <p>
 * 类似{@linkplain AfterRoundEvent 回合后事件}，但额外在反击时触发。
 * <p>
 * 用途包括回合结束和反击后都会触发的效果：
 * 溢彩；
 * 涓流；
 */
public class AfterActionEvent extends BaseEntityEvent {
    public AfterActionEvent(Entity entity) {
        super(entity);
    }
}
