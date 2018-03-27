package com.sine.yys.event;

/**
 * 治疗前事件。
 * @see com.sine.yys.mitama.ShuYao
 */
public class PreCureEvent extends BaseEvent {
    double cure;

    public void setCure(double cure) {
        this.cure = cure;
    }

    public double getCure() {
        return cure;
    }
}
