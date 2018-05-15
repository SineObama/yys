package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 霜天之织进入事件，在御魂效果前触发。
 * <p>
 * 进入和退出必须配套触发（保存了状态以实现取消恢复雪幽魂的触发）。
 */
public class STZZEnterEvent extends BaseVectorEvent {
    private double coefficient = 1.0;

    public STZZEnterEvent(Entity entity, Entity target) {
        super(entity, target);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void multiplyCoefficient(double coefficient) {
        this.coefficient *= coefficient;
    }
}
