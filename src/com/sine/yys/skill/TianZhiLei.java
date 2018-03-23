package com.sine.yys.skill;

import com.sine.yys.buff.debuff.ReduceSpeed;
import com.sine.yys.effect.BaseDebuffEffect;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.RandUtil;

/**
 * 雨女-天之泪。
 */
public class TianZhiLei extends BaseNoTargetSkill {
    private final DebuffEffect effect = new BaseDebuffEffect(getReduceSpeedPct(), getName()) {
        @Override
        public Debuff getDebuff(Entity self) {
            return new ReduceSpeed(getLast(), getName(), getReduceSpeed(), self) {
            };
        }
    };

    @Override
    protected void doApply(Entity target) {
        final Controller controller = getController();
        final Entity self = getSelf();
        for (Entity entity : getEnemy().getAllAlive()) {
            controller.applyDebuff(self, entity, effect);
            controller.dispelBuff(entity, getDispelBuff());
        }
        for (Entity entity : getOwn().getAllAlive()) {
            if (RandUtil.success(getDispelDebuffPct()))
                controller.dispelDebuff(entity, getDispelDebuff());
        }
    }

    /**
     * @return 减速持续回合。
     */
    public int getLast() {
        return 2;
    }

    /**
     * @return 减速概率。
     */
    public double getReduceSpeedPct() {
        return 1.0;
    }

    /**
     * @return 驱散减益概率。
     */
    public double getDispelDebuffPct() {
        return 2.0 / 3.0;
    }

    /**
     * @return 减速数值。
     */
    public double getReduceSpeed() {
        return 20.0;
    }

    /**
     * @return 驱散敌方增益数量。
     */
    public int getDispelBuff() {
        return 2;
    }

    /**
     * @return 驱散我方减益数量。
     */
    public int getDispelDebuff() {
        return 4;
    }

    @Override
    public String getName() {
        return "天之泪";
    }

    @Override
    public int getFire() {
        return 2;
    }
}
