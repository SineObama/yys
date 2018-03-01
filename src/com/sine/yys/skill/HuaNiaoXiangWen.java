package com.sine.yys.skill;

import com.sine.yys.buff.buff.FenFang;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 花鸟卷-花鸟相闻。
 */
public class HuaNiaoXiangWen extends BaseNoTargetSkill {
    @Override
    protected void doApply(Entity target) {
        final Controller controller = getController();
        final Entity self = getSelf();
        for (Entity entity : controller.getCamp(self).getAllAlive()) {  // XXX 群体治疗包括召唤物？
            controller.cure(entity, controller.calcCritical(self, getDirectLifePct() * self.getMaxLife()));
            entity.getBuffController().add(new FenFang(self, getSecondLifePct(), getThirdLifePct()));
        }
    }

    public double getDirectLifePct() {
        return 0.12;
    }

    public double getSecondLifePct() {
        return 0.11;
    }

    public double getThirdLifePct() {
        return 0.10;
    }

    @Override
    public int getFire() {
        return 3;
    }

    @Override
    public String getName() {
        return "花鸟相闻";
    }
}
