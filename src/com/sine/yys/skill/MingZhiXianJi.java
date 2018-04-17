package com.sine.yys.skill;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

/**
 * 童女-命之献祭。
 * <p>
 * 规则：
 * 1. *暂定对召唤物生效；
 * 2. *暂定自身生效的判断在损失生命之后；
 */
public class MingZhiXianJi extends BaseNoTargetSkill {
    @Override
    protected void doApply(Entity target) {
        Entity self = getSelf();
        int count = (int) (self.getLifeInt() * getReducePctByCur());
        log.info(Msg.info(self, "损失生命", count));
        self.reduceLife(count);
        Controller controller = getController();
        final double src = self.getMaxLife() * getCurePctByLife();
        for (Entity entity : getOwn().getAllAlive()) {
            if (entity == self && self.getLife() >= getActiveLessThenPct())
                continue;
            controller.cure(self, entity, src);
        }
    }

    /**
     * @return 减少当前生命的百分比。
     */
    public double getReducePctByCur() {
        return 0.2;
    }

    /**
     * @return 回复生命的百分比。
     */
    public double getCurePctByLife() {
        return 0.2;
    }

    /**
     * @return 当前生命低于百分比对自身生效。
     */
    public double getActiveLessThenPct() {
        return 0.3;
    }

    @Override
    public int getFire() {
        return 1;
    }

    @Override
    public String getName() {
        return "命之献祭";
    }
}
