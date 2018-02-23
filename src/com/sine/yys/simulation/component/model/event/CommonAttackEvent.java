package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;

/**
 * 己方式神普攻事件，保存在阵营中。
 */
public class CommonAttackEvent extends BaseVectorEvent implements Event {
    public CommonAttackEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
