package com.sine.yys.inter;

/**
 * 描述一个回合中式神的操作，包括使用的主动技能和目标。
 * <p>
 * 部分技能不需要指定目标，则允许目标为null。
 */
public interface Operation {
    ActiveSkill getSkill();

    Entity getTarget();
}
