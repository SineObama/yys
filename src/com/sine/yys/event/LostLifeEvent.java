package com.sine.yys.event;

/**
 * 损失生命事件。
 * <p>
 * 记录损失前后生命百分比，损失生命量。
 * <p>
 * 用途：
 * 1. 随生命变化的效果（如赤团华）；
 * 2. 日和坊收集。
 */
public class LostLifeEvent extends BaseEvent {
    final double src;
    final double dst;
    final double count;

    public LostLifeEvent(double src, double dst, double count) {
        this.src = src;
        this.dst = dst;
        this.count = count;
    }

    public double getSrc() {
        return src;
    }

    public double getDst() {
        return dst;
    }

    public double getCount() {
        return count;
    }
}
