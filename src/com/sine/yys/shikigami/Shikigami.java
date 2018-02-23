package com.sine.yys.shikigami;

import com.sine.yys.skill.Skill;
import com.sine.yys.skill.operation.OperationHandler;

import java.util.List;

/**
 * 式神。
 * 包含名字、技能、战斗AI。
 * <p>
 * 无状态，只包含特定式神这一信息，不包含属性、御魂、战斗状态。
 */
public interface Shikigami {
    String getName();

    OperationHandler getAI();

    List<? extends Skill> getSkills();
}
