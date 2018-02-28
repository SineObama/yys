package com.sine.yys.inter;

import com.sine.yys.info.Combinable;
import com.sine.yys.info.IBuffProperty;
import com.sine.yys.info.Named;

/**
 * 式神头上的buff，分为增益和减益两大类。
 * 基本的有增加攻击、防御、抵抗等，也包括护盾、控制效果。
 * 统一实现数值buff以便统计。
 * 需要在行动前后分别调用2个函数，以实现回合数的管理，以及回合前持续伤害的实施。
 * 保存在{@link BuffController}中。
 * <p>
 * 关于回合数：与游戏中显示的数字保持一致，即在回合结束后减1。
 * 其中一个问题是，当前回合给自己（或全队）加的buff，在自己回合结束时并不会减1，比如持续3回合的buff就直接显示3，回合结束后还是3。
 * 当前实现改为：设置行动前后分别调用的2个函数，保存一个状态，使得调用前者再调用后者才让回合数-1。
 */
public interface IBuff extends IBuffProperty, Named, Combinable<IBuff> {
    /**
     * buff名称。
     */
    String getName();

    /**
     * 每回合开始调用，返回剩余持续回合数，0表示效果消失，应当被移除。
     *
     * @param controller 控制器。
     * @param self       式神自身。
     */
    int beforeAction(DamageController controller, Entity self);

    /**
     * 每回合结束调用，返回剩余持续回合数，0表示效果消失，应当被移除。
     *
     * @param controller 控制器。
     * @param self       式神自身。
     */
    int afterAction(DamageController controller, Entity self);

    /**
     * @return 持续回合数。0表示buff应当被移除。永久buff以（接近）整数最大值表示。
     */
    int getLast();

    /**
     * 重复添加同类型buff时调用，因为不能同时存在2个buff。
     * 判断被留下的实例（比如回合数大的保留，或者数值大的保留）。
     * 也可进行回合数叠加（然后返回自己）。
     * <p>
     * 由于在价值相同（一般是回合数相同）的情况下，新buff会替代旧buff（根据UI表现推断），所以约定参数是新的实例。
     *
     * @param o 与实例同类型的buff（新）。
     * @return 被留下的实例。
     */
    IBuff combineWith(IBuff o);

    /**
     * @return 来源式神。
     */
    Entity getSrc();
}
