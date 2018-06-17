package com.sine.yys.inter;

import java.util.Collection;
import java.util.List;

/**
 * 存储攻击信息，包括伤害、暴击、攻击类型。
 * <p>
 * 攻击的类型信息包括：攻击来源（反击、椒图分担、薙魂等等）、一般的附加负面效果、特殊的可传递效果（霜天之织）。
 * 用于实现“信息传递”。
 * <p>
 * 相关例子：
 * * 一般伤害和针女伤害通过涓流分摊，传递给狰式神，实现2次反击。
 * * 食梦貘的攻击通过涓流分摊也不会打醒睡眠。
 * * 霜天之织可经由草人链接或涓流传递一次。
 */
public interface AttackType extends HandlerEffect {
    /**
     * @return 特殊的可传递效果。
     */
    List<TransferrableEffect> getEffects();

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

    /**
     * @return 是否间接伤害。
     */
    boolean isIndirect();

    double getDamage();

    void setDamage(double damage);

    boolean isCritical();

    /**
     * @return 是否原始攻击（触发攻击事件、御魂效果等）。
     */
    boolean isOrigin();

    /**
     * @return 伤害是否可被分摊。
     */
    boolean isSharable();
}
