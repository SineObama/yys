package com.sine.yys.event;

/**
 * 被治疗事件。
 * 记录治疗量。
 */
public class BeCureEvent extends BaseEvent implements Event {
    final double count;

    public BeCureEvent(double count) {
        this.count = count;
    }

    public double getCount() {
        return count;
    }
}
