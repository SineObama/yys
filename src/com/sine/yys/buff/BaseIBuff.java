package com.sine.yys.buff;

import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * buff通用逻辑。
 * <p>
 * 保存buff名字、来源式神、持续回合。
 * 默认所有数值属性为0。
 * 子类重写do*函数实现行动前后的逻辑。
 * <p>
 * 需要在行动前后分别调用{@linkplain #beforeAction(DamageController, Entity)}和{@linkplain #afterAction(DamageController, Entity)}，以实现回合数的管理，以及回合前持续伤害、治疗的实施。
 * <p>
 * 关于回合数：与游戏中显示的数字保持一致，即在回合结束后减1。
 * 其中一个问题是，当前回合给自己（或全队）加的buff，在自己回合结束时并不会减1。比如持续3回合的buff就直接显示3，回合结束后还是3。
 * 当前实现为：设置行动前后分别调用的2个函数，保存一个状态，使得调用前者再调用后者才让回合数-1。
 */
public abstract class BaseIBuff implements IBuff {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private final String name;
    private final Entity src;
    private int last;
    private boolean prepared = false;  // 用于处理buff回合数衰减。调用beforeAction()后为true。在2个状态之间转换

    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     * @param src  来源式神。
     */
    public BaseIBuff(int last, String name, Entity src) {
        this.last = last;
        this.name = name;
        this.src = src;
        if (last <= 0)
            log.warning("buff持续回合不为正。" + this.toString());
    }

    @Override
    public final String toString() {
        return "[" +
                "name=" +
                name +
                "," +
                "src=" +
                String.valueOf(src) +
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
    public final void addLast(int count) {
        if (last <= 0) {
            log.warning("异常调用 addLast()，buff已结束。即将返回。");
            return;
        }
        last += count;
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
    public IBuff replace(Collection<IBuff> buffs) {
        IBuff min = this;
        for (IBuff iBuff : buffs) {
            if (min.compareTo(iBuff) >= 0) {
                min = iBuff;
            }
        }
        return this == min ? null : min;
    }

    /**
     * 每回合开始调用。
     *
     * @param controller 控制器。
     * @param self       所在式神。
     * @return 剩余持续回合数，0表示效果消失，应当被移除。
     */
    public final int beforeAction(DamageController controller, Entity self) {
        doBeforeAction(controller, self);
        if (last <= 0) {
            log.warning("异常调用beforeAction()，buff已结束。即将返回。");
            return 0;
        }
        if (prepared) {
            log.warning("异常调用beforeAction()。prepared=true");
            return last;
        }
        prepared = true;
        return last;
    }

    /**
     * 每回合结束调用。
     * <p>
     * 可能由在自身回合添加buff，导致调用 afterAction，即会返回，无效果。
     *
     * @param controller 控制器。
     * @param self       所在式神。
     * @return 剩余持续回合数，0表示效果消失，应当被移除。
     */
    public final int afterAction(DamageController controller, Entity self) {
        doAfterAction(controller, self);
        if (last <= 0) {
            log.warning("异常调用 afterAction()，buff已结束。即将返回。");
            return 0;
        }
        if (!prepared)
            return last;
        prepared = false;
        last -= 1;
        return last;
    }

    /**
     * buff被移除时的回调。
     *
     * @param self 所在式神。
     */
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
