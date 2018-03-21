package com.sine.yys.inter;

/**
 * 普通攻击。
 * 定义了协战接口。
 */
public interface CommonAttack extends ActiveSkill {
    /**
     * 协战，默认使用与普攻同样的操作。
     */
    void xieZhan(Entity target);

    /**
     * 反击。
     */
    void counter(Entity target);
}
