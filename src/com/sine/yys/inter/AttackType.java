package com.sine.yys.inter;

import java.util.Map;

/**
 * 攻击类型。存储攻击的类型信息：攻击来源（反击、椒图分担、薙魂等等），特殊效果信息（霜天之织）。
 * 用于在伤害分摊时进行信息、效果传递。
 * <p>
 * 但是各个位之间并不能完全组合，有不可能出现的情况，判断上也较为“主观”吧？
 * <p>
 * 例子：
 * 1. 一般伤害和针女伤害通过涓流分摊，传递给狰式神，实现2次反击。
 */
public interface AttackType {
    <T> T getEffect(Class<T> tClass);

    Map<Class, TransferrableEffect> getEffects();

    /**
     * @return 是否反击。
     */
    boolean isCounter();

    /**
     * @see com.sine.yys.mitama.TiHun
     */
    boolean isTiHun();

    /**
     * @see com.sine.yys.skill.JuanLiu
     */
    boolean isJuanLiu();

    /**
     * @see com.sine.yys.mitama.ZhenNv
     */
    boolean isZhenNv();

    boolean isCaoRen();

    boolean isBuff();

    boolean isWake();

    boolean isTrigger();
}
