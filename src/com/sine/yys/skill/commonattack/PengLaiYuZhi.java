package com.sine.yys.skill.commonattack;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 辉夜姬-蓬莱玉枝。
 */
public class PengLaiYuZhi extends BaseCommonAttack implements PctEffect {
    @Override
    public void afterApply(Entity target) {
        super.beforeApply(target);
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            target.getFireRepo().grabFire(1);
        }
    }

    @Override
    public String getName() {
        return "蓬莱玉枝";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.25;
    }

    /**
     * 打掉鬼火概率
     */
    @Override
    public double getPct() {
        return 0.1;
    }
}
