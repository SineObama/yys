package com.sine.yys.skill;

import com.sine.yys.buff.buff.AddAttack;
import com.sine.yys.buff.buff.AddEffectDef;
import com.sine.yys.inter.Entity;

/**
 * 镰鼬-兄弟之绊。
 * 镰鼬以三兄弟的羁绊激励队友，增加全体队友25%的攻击和20%的效果抵抗，持续2回合。同时增加30%的行动条。
 */
public class XiongDiZhiBan extends BaseNoTargetSkill {
    @Override
    public void doApply(Entity target) {
        final Entity self = getSelf();
        for (Entity entity : getOwn().getAllAlive()) {
            entity.addPosition(getAddPosition());
            entity.getBuffController().add(new Attack(getLast(), getName(), getAttackPct(), self));
            entity.getBuffController().add(new EffectDef(getLast(), getName(), getEffectDef(), self));
        }
    }

    @Override
    public int getFire() {
        return 3;
    }

    /**
     * @return 行动条增加数值。
     */
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

    public class Attack extends AddAttack {
        Attack(int last, String prefix, double atkPct, Entity src) {
            super(last, prefix, atkPct, src);
        }
    }

    public class EffectDef extends AddEffectDef {
        EffectDef(int last, String prefix, double effectDef, Entity src) {
            super(last, prefix, effectDef, src);
        }
    }
}
