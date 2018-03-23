package com.sine.yys.inter;

import java.util.Collection;

/**
 * 一次攻击（一次伤害）中的信息。
 * <p>
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
