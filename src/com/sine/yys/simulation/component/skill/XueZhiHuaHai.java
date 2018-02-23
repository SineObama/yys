package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.buff.shield.XueZhiHuaHaiShield;
import com.sine.yys.simulation.component.inter.Controller;
import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.util.Msg;

/**
 * 彼岸花-血之花海。
 */
public class XueZhiHuaHai extends BaseNoTargetSkill implements ActiveSkill {
    private static final String LEVEL = "LEVEL";

    /**
     * @return 血之花海护盾，已损生命的百分比。
     */
    public double getLostLifePct() {
        return 0.28;
    }

    @Override
    public int getFire() {
        return 3;
    }

    @Override
    public void doApply(Controller controller, Entity self, Entity target) {
        int count = 2;
        if (self.getLife() < 0.5)
            count += 1;
        addLevel(self, count);
        if (self.getLife() < 0.75)
            addShield(self);
    }

    public void addLevel(Entity self, int count) {
        final int max = (int) (7 - self.getLife() * 4);
        int level = getLevel(self);
        log.info(Msg.info(self, "原花海层数 " + level));
        level += count;
        if (level > max)
            level = max;
        self.put(this.getClass(), LEVEL, level);
        log.info(Msg.info(self, "现花海层数 " + level));
    }

    public int getLevel(Entity self) {
        return self.get(this.getClass(), LEVEL, 3);
    }

    @Override
    public int step(Entity self) {
        int rtn = super.step(self);
        int level = getLevel(self);
        if (level > 0) {
            level -= 1;
            self.put(this.getClass(), LEVEL, level);
        }
        return rtn;
    }

    @Override
    public String getName() {
        return "血之花海";
    }

    public void addShield(Entity self) {
        final double value = (int) (self.getMaxLife() * (1 - self.getLife()) * getLostLifePct());
        self.getBuffController().addShield(new XueZhiHuaHaiShield(self.shieldValue(value)));
        log.info(Msg.info(self, "获得 " + getName()));
    }
}
