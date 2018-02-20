package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.Entity;

/**
 * 使用鬼火事件。
 * 目前只保存最终消耗的鬼火，用于《明灯》不消耗鬼火。
 */
public class UseFireEvent implements Event {
    private Entity self;
    private int costFire;

    public UseFireEvent(Entity self, int costFire) {
        this.self = self;
        this.costFire = costFire;
    }

    public Entity getSelf() {
        return self;
    }

    public int getCostFire() {
        return costFire;
    }

    public void setCostFire(int costFire) {
        this.costFire = costFire;
    }
}
