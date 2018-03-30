package com.sine.yys.inter;

/**
 * 攻击类型。
 * <p>
 * 保存了攻击来源（反击、椒图分担、薙魂等等）。
 * <p>
 * 最简单的例子是攻击和针女伤害通过椒图分摊，传递给狰式神，实现2次反击。
 */
public interface AttackType {
    /**
     * @return 是否反击。
     */
    boolean isCounter();

    void setCounter(boolean counter);

    /**
     * @see com.sine.yys.mitama.TiHun
     */
    boolean isTiHun();

    void setTiHun(boolean tiHun);

    /**
     * @see com.sine.yys.skill.JuanLiu
     */
    boolean isJuanLiu();

    void setJuanLiu(boolean juanLiu);

    /**
     * @see com.sine.yys.mitama.ZhenNv
     */
    boolean isZhenNv();

    void setZhenNv(boolean zhenNv);

    boolean isBuff();

    void setBuff(boolean buff);
}
