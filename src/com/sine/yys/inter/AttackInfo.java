package com.sine.yys.inter;

import java.util.Collection;

/**
 * 定义一次攻击（一次伤害）中，只与技能本身相关的信息。
 * 包括：系数，忽略防御百分比，忽略防御数值，伤害浮动，附加负面效果。
 */
public interface AttackInfo {
    double getCoefficient();

    double getIgnoreDefensePct();

    int getIgnoreDefense();

    /**
     * @return 随机的浮动系数。
     */
    double randomFloat();

    /**
     * 附加负面效果。
     */
    Collection<DebuffEffect> getDebuffEffects();
}
