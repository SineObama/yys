package com.sine.yys.buff;

import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

import java.util.logging.Logger;

/**
 * buff通用逻辑。
 * <p>
 * 保存buff名字、来源式神、持续回合。
 * 默认所有数值属性为0。
 * <p>
 * 根据减少回合数的时机（行动前和行动后）区分为2类效果。
 * 提供行动前后的2个调用接口，子类重写do*函数实现各自逻辑。
 */
public abstract class BaseIBuff implements IBuff {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private final String name;
    private final Entity src;
    int last;
    private boolean prepared = false;  // 用于处理buff回合数衰减。调用beforeAction()后为true。在2个状态之间转换

    /**
     * @param last 持续回合数。必须为正。
     * @param src  来源式神。
     * @param name buff名称。
     */
    public BaseIBuff(int last, Entity src, String name) {
        this.name = name;
        this.src = src;
        this.last = last;
        if (last <= 0)
            log.warning("buff持续回合不为正。" + this.toString());
    }

    @Override
    public String toString() {
        return "[" +
                "name=" +
                name +
                "," +
                "src=" +
                (src == null ? "null" : src.getFullName()) +
                "," +
                "last=" +
                last +
                "," +
                "]";
    }

    @Override
    public final int getLast() {
        return last;
    }

    @Override
    public final int addLast(int count) {
        if (last <= 0) {
            log.warning("异常调用 addLast()，buff已结束。即将返回。");
            return 0;
        }
        return last += count;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Entity getSrc() {
        return src;
    }

    @Override
    public int compareTo(IBuff o) {
        return getLast() - o.getLast();
    }

    @Override
    public final int beforeAction(DamageController controller, Entity self) {
        if (last <= 0) {
            log.warning("异常调用beforeAction()，buff已结束。即将返回。");
            return 0;
        }
        if (prepared) {
            log.warning("异常调用beforeAction()。prepared=true");
            return last;
        }
        prepared = true;
        doBeforeAction(controller, self);
        dealLastBeforeAction();
        return last;
    }

    /**
     * 可能由在自身回合添加buff，导致调用 afterAction，即会返回，无效果。
     */
    @Override
    public final int afterAction(DamageController controller, Entity self) {
        if (last <= 0) {
            log.warning("异常调用 afterAction()，buff已结束。即将返回。");
            return 0;
        }
        if (!prepared)
            return last;
        prepared = false;
        doAfterAction(controller, self);
        dealLastAfterAction();
        return last;
    }

    void dealLastBeforeAction() {
    }

    void dealLastAfterAction() {
    }

    @Override
    public void onRemove(Entity self) {
    }

    protected void doBeforeAction(DamageController controller, Entity self) {
    }

    protected void doAfterAction(DamageController controller, Entity self) {
    }

    @Override
    public double getBeDamage() {
        return 0;
    }

    @Override
    public double getCure() {
        return 0;
    }

    @Override
    public double getDamageUp() {
        return 0;
    }

    @Override
    public double getFlagDamage() {
        return 0;
    }

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
