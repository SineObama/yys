package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseCommonIBuff;

/**
 * 战场旗帜效果，随战场鲤鱼旗行动逐渐加强。
 * 造成伤害增加，治疗减少。
 * 不可驱散。
 */
public class BattleFlag extends BaseCommonIBuff {
    private final BattleFlagSource source;

    public BattleFlag(BattleFlagSource source) {
        super(Integer.MAX_VALUE, "战场效果", null);
        this.source = source;
    }

    @Override
    public double getCure() {
        return source.getCure();
    }

    @Override
    public double getDamageUp() {
        return source.getDamageUp();
    }

    @Override
    public double getFlagDamage() {
        return source.getDamageUp();
    }
}
