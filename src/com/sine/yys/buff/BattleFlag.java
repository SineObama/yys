package com.sine.yys.buff;

import com.sine.yys.inter.IBuff;

/**
 * 战场旗帜效果，随裁判旗子行动逐渐加强。不可驱散。
 * <p>
 * 目的是使全场造成伤害增加，治疗减少。
 */
public class BattleFlag extends BaseIBuff {
    private final BattleFlagSource source;

    public BattleFlag(BattleFlagSource source) {
        super(Integer.MAX_VALUE, "战场旗帜效果", null);
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

    @Override
    public int compareTo(IBuff o) {
        return 0;
    }
}
