package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.attacktype.BuffAttack;
import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

/**
 * 持续伤害。
 */
public abstract class BaseDoT extends BaseIBuff {
    protected BaseDoT(int last, String prefix, Entity src) {
        super(last, prefix + "-持续伤害", src);
    }

    @Override
    protected final void doBeforeAction(DamageController controller, Entity self) {
        log.info(Msg.info(self, "受到效果", getName()));
        double damage = handle(self);
        controller.attack(getSrc(), self, new BuffAttack(damage));
    }

    /**
     * @param self 所在式神。
     * @return 计算得到的伤害数值（包括暴击）。
     */
    abstract double handle(Entity self);
}
