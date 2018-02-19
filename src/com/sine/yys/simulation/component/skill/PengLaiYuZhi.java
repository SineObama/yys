package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.entity.BaseEntity;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 辉夜姬-蓬莱玉枝。
 */
public class PengLaiYuZhi extends BaseCommonAttack {
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
    public double getPct() {
        return 0.1;
    }

    @Override
    public void doApply(BaseEntity target) {
        super.doApply(target);
        if (RandUtil.success(getPct()) && target.getFireRepo().grabFire(1) > 0)
            log.info(Msg.vector(getSelf(), "打掉", target, "1 点鬼火"));
    }
}
