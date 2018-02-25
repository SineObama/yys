package com.sine.yys.event;

import com.sine.yys.inter.Controller;

/**
 * 行动结束时，镰鼬专用，只在可行动的回合触发，强控下不触发。
 */
public class FinishActionEvent extends BaseEvent implements Event {
    public FinishActionEvent(Controller controller) {
        super(controller);
    }
}
