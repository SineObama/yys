package com.sine.yys.simulation.component;

import com.sine.yys.info.Target;
import com.sine.yys.inter.Controller;

import java.util.logging.Logger;

/**
 * 战场中一个对象。
 * 包括可被选择为目标的实体（Entity，式神或召唤物）、和战场中的其他对象（战场鲤鱼旗、竞赛秘闻鬼面）。
 */
public abstract class SimpleObject implements Target {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private final String name;
    private final double speed;
    private double position = 0;  // 行动位置，范围0-1。
    private Controller controller;

    protected SimpleObject(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    final void init(Controller controller) {
        this.controller = controller;
        doInit();
    }

    abstract void action();

    protected abstract void doInit();

    protected Controller getController() {
        return controller;
    }

    public double getSpeed() {
        return speed;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void addPosition(double count) {
        this.position += count;
        if (this.position > 1.0)
            this.position = 1.0;
    }

    @Override
    public String getFullName() {
        return getName();
    }

    @Override
    public final String getName() {
        return name;
    }
}
