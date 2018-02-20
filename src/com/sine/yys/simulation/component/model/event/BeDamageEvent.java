package com.sine.yys.simulation.component.model.event;

/**
 * 受到伤害事件。多段攻击也算。记录受伤前后生命百分比。
 * 死亡不算。
 */
public class BeDamageEvent implements Event {
    double src;
    double dst;

    public BeDamageEvent(double src, double dst) {
        this.src = src;
        this.dst = dst;
    }

    public double getSrc() {
        return src;
    }

    public double getDst() {
        return dst;
    }
}
