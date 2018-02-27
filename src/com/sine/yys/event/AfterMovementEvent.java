package com.sine.yys.event;

import com.sine.yys.inter.Controller;

/**
 * 新定义“动作”。
 * 具体包括一次行动前事件，一次行动，一次反击。
 *
 * @version 2018-2-28
 */
public class AfterMovementEvent extends BaseEvent {
    public AfterMovementEvent(Controller controller) {
        super(controller);
    }
}
