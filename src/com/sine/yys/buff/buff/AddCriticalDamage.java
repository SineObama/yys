package com.sine.yys.buff.buff;

import com.sine.yys.buff.CriticalDamageIBuff;
import com.sine.yys.inter.Entity;

public abstract class AddCriticalDamage extends CriticalDamageIBuff {
    /**
     * @param last           持续回合数。必须为正。
     * @param prefix         名称前缀。
     * @param criticalDamage 暴伤。
     * @param src            来源式神。
     */
    protected AddCriticalDamage(int last, String prefix, double criticalDamage, Entity src) {
        super(last, prefix + "-增加", criticalDamage, src);
    }
}
