package com.sine.yys.inter;

/**
 * 实体的拓展。
 * <p>
 * 为了消除循环引用，技能和御魂对于所属式神保留此引用，以获取普通攻击。
 * 同时删除了技能和御魂的初始化接口。
 */
public interface Self extends Entity {
    CommonAttack getCommonAttack();
}
