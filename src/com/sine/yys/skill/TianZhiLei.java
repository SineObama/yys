package com.sine.yys.skill;

import com.sine.yys.buff.debuff.ReduceSpeed;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.RandUtil;

/**
 * 雨女-天之泪。
 */
public class TianZhiLei extends BaseNoTargetSkill implements DebuffEffect {
    @Override
    protected void doApply(Entity target) {
        final Controller controller = getController();
        final Entity self = getSelf();
        for (Entity entity : getEnemy().getAllAlive()) {
            controller.applyDebuff(self, entity, this);
            controller.dispelBuff(entity, getMaxDispelBuff());
        }
        for (Entity entity : getOwn().getAllAlive()) {
            if (RandUtil.success(getDispelDebuffPct()))
                controller.dispelDebuff(entity, getMaxDispelDebuff());
        }
    }

    @Override
    public String getName() {
        return "天之泪";
    }

    @Override
    public int getFire() {
        return 2;
    }

    /**
     * @return 驱散敌方增益。
     */
    public int getMaxDispelBuff() {
        return 2;
    }

    /**
     * @return 驱散我方减益。
     */
    public int getMaxDispelDebuff() {
        return 4;
    }

    /**
     * @return 驱散我方减益。
     */
    public double getDispelDebuffPct() {
        return 2.0 / 3.0;
    }

    /**
     * @return 减速数值。
     */
    public double getReduceSpeed() {
        return 20;
    }

    @Override
    public double getPct() {
        return 1.0;
    }

    @Override
    public boolean involveHitAndDef() {
        return true;
    }

    @Override
    public Debuff getDebuff(Entity self) {
        return new ReduceSpeed(2, getName(), getReduceSpeed(), self) {
        };
    }
}
