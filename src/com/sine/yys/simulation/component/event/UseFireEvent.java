package com.sine.yys.simulation.component.event;

/**
 * 使用鬼火事件。
 * 目前只保存最终消耗的鬼火，用于《明灯》不消耗鬼火。
 */
public class UseFireEvent implements Event {
    private int costFire;

    public UseFireEvent(int costFire) {
        this.costFire = costFire;
    }

    public int getCostFire() {
        return costFire;
    }

    public void setCostFire(int costFire) {
        this.costFire = costFire;
    }
}
