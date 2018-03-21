package com.sine.yys.inter;

/**
 * 攻击类型。
 * <p>
 * 以此传递攻击信息。保存了攻击来源（反击、椒图分担、薙魂等等）。
 */
public interface AttackType {
    boolean isCounter();

    void setCounter(boolean counter);

    /**
     * @see com.sine.yys.mitama.TiHun
     */
    boolean isTiHun();

    void setTiHun(boolean tiHun);

    boolean isJiaoTu();

    void setJiaoTu(boolean jiaoTu);

    /**
     * @see com.sine.yys.mitama.ZhenNv
     */
    boolean isZhenNv();

    void setZhenNv(boolean zhenNv);
}
