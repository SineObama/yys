package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.EffectDefIBuff;
import com.sine.yys.inter.Entity;

/**
 * 童女-羽刺。
 */
public class YuCi extends BaseCommonAttack {
    @Override
    protected void afterApply(Entity target) {
        // 羽刺的效果抵抗不能驱散。
        getSelf().getBuffController().add(new EffectDefIBuff(getLast(), getName(), getAddEffectDef(), getSelf()) {
        });
    }

    public int getLast() {
        return 1;
    }

    public double getAddEffectDef() {
        return 0.1;
    }

    @Override
    public String getName() {
        return "羽刺";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.20;
    }
}
