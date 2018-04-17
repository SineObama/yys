package com.sine.yys.inter;

import com.sine.yys.inter.base.Named;

import java.util.Collection;

/**
 * 式神。
 * <p>
 * 包含名字、技能、战斗AI等信息。
 * <p>
 * 无状态，只包含特定式神这一信息，不包含属性、御魂、战斗状态。
 */
public interface Shikigami extends Named {
    OperationHandler getAI();

    Collection<? extends Skill> getSkills();

    /**
     * @return 式神面板上的原始攻击。是一般攻击加成的基数。
     */
    double getOriginAttack();
}
