package com.sine.yys.buff;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

import java.util.logging.Logger;

/**
 * buff通用逻辑。
 * 提供行动前后的2个调用接口，子类重写do*函数实现各自逻辑。
 * 保存buff名字。
 * 默认所有数值属性为0。
 */
public abstract class BaseIBuff implements IBuff {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    private final String name;
    private boolean prepared = false;  // 用于处理buff回合数衰减。调用beforeAction()后为true。在2个状态之间转换

    public BaseIBuff(String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int beforeAction(Controller controller, Entity self) {
        if (prepared) {
            log.severe("异常调用beforeAction()");
            return getLast();
        }
        prepared = true;
        return doBeforeAction(controller, self);
    }

    @Override
    public final int afterAction(Controller controller, Entity self) {
        if (!prepared)
            return getLast();
        prepared = false;
        return doAfterAction(controller, self);
    }

    protected abstract int doBeforeAction(Controller controller, Entity self);

    protected abstract int doAfterAction(Controller controller, Entity self);

    public abstract int getLast();

    @Override
    public double getAtkPct() {
        return 0;
    }

    @Override
    public double getDefPct() {
        return 0;
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public double getCritical() {
        return 0;
    }

    @Override
    public double getCriticalDamage() {
        return 0;
    }

    @Override
    public double getEffectHit() {
        return 0;
    }

    @Override
    public double getEffectDef() {
        return 0;
    }
}
