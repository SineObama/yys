package com.sine.yys.inter;

import com.sine.yys.attacktype.OriginAttackType;

import java.util.List;

/**
 * 存储攻击信息，包括伤害、暴击、其他攻击类型信息。
 * <p>
 * 攻击的类型信息包括：攻击来源（反击、椒图分担、薙魂等等）、一般的附加负面效果、特殊的可传递效果（霜天之织）。
 * 其他还包括是否打醒睡眠、是否触发御魂和被动效果等。
 * <p>
 * 攻击有一定的传递和继承性，比如反击触发的针女是反击针女；
 * 反击触发了的薙魂、涓流，受伤者受到的攻击也是反击类型的；
 * 通过涓流传递的针女伤害也可以让狰式神反击第二次。
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
     * @see OriginAttackType
     */
    boolean isOrigin();

    /**
     * @return 伤害是否可被分摊。
     */
    boolean isSharable();
}
