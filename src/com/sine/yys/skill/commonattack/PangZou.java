package com.sine.yys.skill.commonattack;

import com.sine.yys.effect.PangZouEffect;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;

import java.util.Collection;
import java.util.Collections;

/**
 * 镰鼬-胖揍。
 */
public class PangZou extends BaseCommonAttack {
    private DebuffEffect effect = new PangZouEffect(getPct());

    @Override
    public String getName() {
        return "胖揍";
    }

    @Override
    public double getCoefficient() {
        return 1.0 * 1.2;
    }

    /**
     * @return 附加效果概率
     */
    public double getPct() {
        return 0.2;
    }

    @Override
    protected Collection<DebuffEffect> getDebuffEffects() {
        return Collections.singleton(effect);
    }

    @Override
    protected void doApply(Controller controller, Entity self, Entity target) {
        super.doApply(controller, self, target);
    }
}
