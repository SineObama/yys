package com.sine.yys.inter;

import java.util.Collection;

/**
 * 式神。
 * 包含名字、技能、战斗AI。
 * <p>
 * 无状态，只包含特定式神这一信息，不包含属性、御魂、战斗状态。
 */
public interface Shikigami extends Named {
    OperationHandler getAI();

    Collection<? extends Skill> getSkills();

    double getOriginAttack();
}
