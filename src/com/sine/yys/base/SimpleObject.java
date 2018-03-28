package com.sine.yys.base;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.base.Target;

import java.util.logging.Logger;

/**
 * 战场中一个对象，包括可被选择为目标的{@linkplain com.sine.yys.inter.Entity 实体}（式神或召唤物）、战场中的其他对象（{@linkplain com.sine.yys.simulation.component.BattleKoinobori 裁判旗子}、竞赛秘闻鬼面）。
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

    public final void init(Controller controller) {
        this.controller = controller;
        doInit();
    }

    /**
     * 对象自身的行动逻辑。
     */
    public abstract void action();

    protected abstract void doInit();

    protected final Controller getController() {
        return controller;
    }

    public double getSpeed() {
        return speed;
    }

    public final double getPosition() {
        return position;
    }

    public final void setPosition(double position) {
        this.position = position;
    }

    public final void addPosition(double count) {
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

    @Override
    public final String toString() {
        return getFullName();
    }
}
