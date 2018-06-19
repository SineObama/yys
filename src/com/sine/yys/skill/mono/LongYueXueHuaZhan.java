package com.sine.yys.skill.mono;

import com.sine.yys.buff.EffectHitIBuff;
import com.sine.yys.inter.BuffController;
import com.sine.yys.inter.Entity;

/**
 * 雪童子-胧月雪华斩。
 */
public class LongYueXueHuaZhan extends BaseDirectiveSkill {
    private EffectHitIBuff effectHitIBuff;

    @Override
    protected void beforeApply(Entity target) {
        super.beforeApply(target);
        final BuffController buffController = getSelf().getBuffController();
        effectHitIBuff = new EffectHitIBuff(1, getName(), buffController.getEffectHit(), getSelf()) {
        };
        buffController.add(effectHitIBuff);
    }

    @Override
    protected void afterApply(Entity target) {
        super.beforeApply(target);
        getSelf().getBuffController().remove(effectHitIBuff);
    }

    @Override
    public double getCoefficient() {
        return 0.8;
    }

    @Override
    public int getFire() {
        return 3;
    }

    @Override
    public int getTimes() {
        return 3;
    }

    @Override
    public String getName() {
        return "胧月雪华斩";
    }
}
