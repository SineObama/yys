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
    public void apply(Entity target) {
        int count = 2;
        if (getSelf().getLife() < 0.5)
            count += 1;
        addLevel(count);
        if (getSelf().getLife() < 0.75)
            addShield();
    }

    public void addLevel(int count) {
        final int max = (int) (7 - getSelf().getLife() * 4);
        log.info(Msg.info(getSelf(), "原花海层数 " + level));
        level += count;
        if (level > max)
            level = max;
        log.info(Msg.info(getSelf(), "现花海层数 " + level));
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int step() {
        int rtn = super.step();
        if (level > 0)
            level -= 1;
        return rtn;
    }

    @Override
    public String getName() {
        return "血之花海";
    }

    public void addShield() {
        final int value = (int) (getSelf().getMaxLife() * (1 - getSelf().getLife()));
        getSelf().getBuffController().addShield(new XueZhiHuaHaiShield(value));
        log.info(Msg.info(getSelf(), "获得 " + getName()));
    }
}
