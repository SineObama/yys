package com.sine.yys.skill;

import com.sine.yys.buff.shield.XueZhiHuaHaiShield;
import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

/**
 * 彼岸花-血之花海。
 * 当前在回合前减1层。
 */
public class XueZhiHuaHai extends BaseNoTargetSkill implements ActiveSkill {
    public static final String LEVEL = "HuaHai_LEVEL";

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
    public void doApply(Entity target) {
        int count = 2;
        if (getSelf().getLife() < 0.5)
            count += 1;
        addLevel(count);
        if (getSelf().getLife() < 0.75)
            addShield();
    }

    public void addLevel(int count) {
        final Entity self = getSelf();
        final int max = getMaxLevel(self.getLife());
        int level = getLevel();
        log.info(Msg.info(self, "原花海层数 " + level));
        level += count;
        if (level > max)
            level = max;
        self.put(LEVEL, level);
        log.info(Msg.info(self, "现花海层数 " + level));
    }

    public int getMaxLevel(double lifePct) {
        if (lifePct == 0.0)
            return 6;
        return (int) (7 - lifePct * 4);
    }

    public int getLevel() {
        return getSelf().get(LEVEL, 3);
    }

    @Override
    public void doBeforeAction() {
        int level = getLevel();
        if (level > 0) {
            level -= 1;
            getSelf().put(LEVEL, level);
        }
    }

    @Override
    public String getName() {
        return "血之花海";
    }

    public void addShield() {
        final Entity self = getSelf();
        final double value = self.getLostLifeInt() * getLostLifePct();
        self.getBuffController().add(new XueZhiHuaHaiShield(getController().calcCritical(self, value), self));
        log.info(Msg.info(self, "获得 " + getName()));
    }
}
