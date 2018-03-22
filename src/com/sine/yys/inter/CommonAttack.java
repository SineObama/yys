package com.sine.yys.inter;

/**
 * 普通攻击。
 * <p>
 * 定义了协战、反击接口。
 */
public interface CommonAttack extends ActiveSkill {
    /**
     * @param target 协战的攻击目标。
     */
    void xieZhan(Entity target);

    /**
     * @param target 反击的攻击目标。
     */
    void counter(Entity target);
}
