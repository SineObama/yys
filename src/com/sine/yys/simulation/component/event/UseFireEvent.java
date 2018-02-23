package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.inter.Controller;
import com.sine.yys.simulation.component.inter.Entity;

/**
 * 使用鬼火事件。
 * 目前只保存最终消耗的鬼火，用于《明灯》不消耗鬼火。
 */
public class UseFireEvent extends BaseEntityEvent implements Event {
    private int costFire;

    public UseFireEvent(Controller controller, Entity self, int costFire) {
        super(controller, self);
        this.costFire = costFire;
    }

    public int getCostFire() {
        return costFire;
    }

    public void setCostFire(int costFire) {
        this.costFire = costFire;
    }
}
