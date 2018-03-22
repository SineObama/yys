package com.sine.yys.skill;

import com.sine.yys.buff.buff.CureBuff;
import com.sine.yys.buff.buff.DispellableBuff;
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
        for (Entity entity : getOwn().getAllAlive()) {  // XXX 群体治疗包括召唤物？
            controller.cure(self, entity, getDirectLifePct() * self.getMaxLife());
            entity.getBuffController().add(new FenFang(self));
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

    public int getLast() {
        return 2;
    }

    @Override
    public String getName() {
        return "花鸟相闻";
    }

    public class FenFang extends CureBuff implements DispellableBuff {
        FenFang(Entity src) {
            super(HuaNiaoXiangWen.this.getLast(), HuaNiaoXiangWen.this.getName() + "-芬芳", src);
        }

        @Override
        protected double getCureCount() {
            if (getLast() == 2) {
                return getSecondLifePct() * getSrc().getMaxLife();
            } else if (getLast() == 1) {
                return getThirdLifePct() * getSrc().getMaxLife();
            }
            return 0;
        }
    }
}
