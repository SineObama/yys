package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.info.AttackInfo;

/**
 * 普通攻击。
 * 定义了协战接口。
 */
public interface CommonAttack extends AttackSkill {
    /**
     * 协战，默认使用与普攻同样的操作。
     */
    void xieZhan(Controller controller, Entity self, Entity target);

    /**
     * 获取攻击属性。
     * 目前包括系数，忽略防御。
     */
    AttackInfo getAttack();
}
