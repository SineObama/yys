package com.sine.yys.inter;

import com.sine.yys.inter.base.IBuffProperty;
import com.sine.yys.inter.base.Named;

/**
 * 式神头上显示的buff。
 * <p>
 * 一般可分为增益和减益两大类。
 * 有数值类型的（增加攻击、防御、抵抗等），也有护盾、控制效果。
 * <p>
 * 统一实现{@linkplain IBuffProperty}以便统计。
 * <p>
 * 需要在行动前后分别调用2个函数，以实现回合数的管理，以及回合前持续伤害、治疗的实施。
 *
 * @see BuffController
 */
public interface IBuff extends IBuffProperty, Named, Comparable<IBuff> {
    /**
     * @param count 增加持续回合的数量。
     */
    void addLast(int count);

    /**
     * 每回合开始调用。
     *
     * @param controller 控制器。
     * @param self       所在式神。
     * @return 剩余持续回合数，0表示效果消失，应当被移除。
     */
    int beforeAction(DamageController controller, Entity self);

    /**
     * 每回合结束调用。
     *
     * @param controller 控制器。
     * @param self       所在式神。
     * @return 剩余持续回合数，0表示效果消失，应当被移除。
     */
    int afterAction(DamageController controller, Entity self);

    /**
     * @return 持续回合数。0表示buff应当被移除。永久buff以（接近）整数最大值表示。
     */
    int getLast();

    /**
     * @return 来源式神。
     */
    Entity getSrc();

    /**
     * buff被移除时的回调。
     *
     * @param self 所在式神。
     */
    void onRemove(Entity self);
}
