package com.sine.yys.buff;

import com.sine.yys.info.IBuffProperty;
import com.sine.yys.info.Named;

/**
 * 式神身上的buff，分为增益和减益两大类。
 * 基本的有增加攻击、防御、抵抗等，也包括护盾、控制效果。
 * 以类型本身作为标记，即同一个类的buff只能存在一个，同类buff会调用叠加函数。
 * <p>
 * 统一实现数值buff以便统计。
 * 不附带战场逻辑，在{@link com.sine.yys.inter.BuffController}中进行管理。
 * <p>
 * 关于回合数：与游戏中显示的数字保持一致，即在回合结束后减1。
 * 其中一个问题是，当前回合给自己（或全队）加的buff，在自己回合结束时并不会减1，比如持续3回合的buff就直接显示3，回合结束后还是3。
 * 目前的处理是在对自身的buff额外增加1回合。
 * 考虑另一种实现是在回合开始时“保存”已有buff，在结束时对这些进行减1，但似乎更麻烦更慢。
 */
public interface IBuff extends IBuffProperty, Named {
    /**
     * buff名称。
     */
    String getName();

    /**
     * 每回合结束调用，返回剩余持续回合数，0表示消失。
     */
    int step();

    /**
     * 持续回合数。
     */
    int getLast();
}
