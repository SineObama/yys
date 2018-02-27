package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 己方式神普攻事件，保存在阵营中。
 */
public class CommonAttackEvent extends BaseVectorEvent implements Event {
    public CommonAttackEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
