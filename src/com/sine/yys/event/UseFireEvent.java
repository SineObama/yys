package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 使用鬼火事件。
 * 目前只保存最终消耗的鬼火，用于《明灯》不消耗鬼火。
 */
public class UseFireEvent extends BaseEntityEvent implements Event {
    private int costFire;

    public UseFireEvent(Entity self, int costFire) {
        super(self);
        this.costFire = costFire;
    }

    public int getCostFire() {
        return costFire;
    }

    public void setCostFire(int costFire) {
        this.costFire = costFire;
    }
}
