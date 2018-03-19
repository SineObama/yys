package com.sine.yys.event;

/**
 * 受到伤害事件。多段攻击也算。
 * 记录受伤前后生命百分比。
 * 记录损失生命量。
 */
public class LostLifeEvent extends BaseEvent implements Event {
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
