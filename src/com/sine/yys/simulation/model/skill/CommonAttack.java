package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.model.Attack;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 普通攻击。
 */
public interface CommonAttack extends ActiveSkill {
    /**
     * 协战专用函数，默认使用与普攻同样的操作。
     */
    void xieZhan(Entity target, Controller controller);

    /**
     * 普通攻击实际操作。目前只施加伤害。
     * 独立出普攻和协战的共同部分定义为此。
     * 子类应重写此函数而不是apply。
     */
    void doApply(Entity target, Controller controller);

    /**
     * 获取攻击属性。
     * 目前包括系数，忽略防御。
     */
    Attack getAttack();
}
