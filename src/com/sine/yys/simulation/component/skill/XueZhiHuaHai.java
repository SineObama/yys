package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.shield.XueZhiHuaHaiShield;
import com.sine.yys.simulation.util.Msg;

/**
 * 彼岸花-血之花海。
 */
public class XueZhiHuaHai extends BaseNoTargetSkill implements ActiveSkill {
    private int level = 3;

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
    public void doApply(Entity self, Entity target) {
        int count = 2;
        if (self.getLife() < 0.5)
            count += 1;
        addLevel(self, count);
        if (self.getLife() < 0.75)
            addShield(self);
    }

    public void addLevel(Entity self, int count) {
        final int max = (int) (7 - self.getLife() * 4);
        log.info(Msg.info(self, "原花海层数 " + level));
        level += count;
        if (level > max)
            level = max;
        log.info(Msg.info(self, "现花海层数 " + level));
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int step(Entity self) {
        int rtn = super.step(self);
        if (level > 0)
            level -= 1;
        return rtn;
    }

    @Override
    public String getName() {
        return "血之花海";
    }

    public void addShield(Entity self) {
        final int value = (int) (self.getMaxLife() * (1 - self.getLife()));
        self.getBuffController().addShield(new XueZhiHuaHaiShield(value));
        log.info(Msg.info(self, "获得 " + getName()));
    }
}
