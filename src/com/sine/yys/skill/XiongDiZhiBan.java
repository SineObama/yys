package com.sine.yys.skill;

import com.sine.yys.buff.buff.XDZBAttackBuff;
import com.sine.yys.buff.buff.XDZBEffectDefBuff;
import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 镰鼬-兄弟之绊。
 * 镰鼬以三兄弟的羁绊激励队友，增加全体队友25%的攻击和20%的效果抵抗，持续2回合。同时增加30%的行动条。
 */
public class XiongDiZhiBan extends BaseNoTargetSkill implements ActiveSkill {
    @Override
    public int getFire() {
        return 3;
    }

    @Override
    public void doApply(Controller controller, Entity self, Entity target) {
        for (Entity entity : controller.getCamp(self).getAllAlive()) {
            entity.addPosition(getAddPosition());
            entity.getBuffController().add(new XDZBAttackBuff(getLast(), getAttackPct(), self));
            entity.getBuffController().add(new XDZBEffectDefBuff(getLast(), getEffectDef(), self));
        }
    }

    public double getAddPosition() {
        return 0.3;
    }

    public double getAttackPct() {
        return 0.25;
    }

    public double getEffectDef() {
        return 0.2;
    }

    @Override
    public String getName() {
        return "兄弟之绊";
    }

    public int getLast() {
        return 2;
    }
}
