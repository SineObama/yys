package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 普通攻击。
 * 定义了协战接口。
 */
public interface CommonAttack extends AttackSkill {
    /**
     * 协战专用函数，默认使用与普攻同样的操作。
     */
    void xieZhan(Entity target);

    /**
     * 获取攻击属性。
     * 目前包括系数，忽略防御。
     */
    AttackInfo getAttack();
}
