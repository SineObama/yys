package com.sine.yys.inter;

/**
 * 一个回合中为式神选择的操作。
 * 包括使用的主动技能和目标。
 * 目标可能为null，比如这个技能就是对敌方全体释放的情况。
 */
public interface Operation {
    ActiveSkill getSkill();

    Entity getTarget();
}
