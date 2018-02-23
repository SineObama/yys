package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.Controller;

/**
 * 受到伤害事件。多段攻击也算。记录受伤前后生命百分比。
 * 死亡不算。
 */
public class BeDamageEvent extends BaseEvent implements Event {
    double src;
    double dst;

    public BeDamageEvent(Controller controller, double src, double dst) {
        super(controller);
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
