package com.sine.yys.inter;

/**
 * 实体的拓展。
 * <p>
 * 为了消除循环引用，技能和御魂对于自身所属式神保留此引用，用以获取普通攻击。
 */
public interface Self extends Entity {
    CommonAttack getCommonAttack();
}
