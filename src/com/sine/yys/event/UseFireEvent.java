package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 使用鬼火事件。
 * <p>
 * 保存最终决定消耗的鬼火。
 * 用途：明灯。
 */
public class UseFireEvent extends BaseEntityEvent {
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
