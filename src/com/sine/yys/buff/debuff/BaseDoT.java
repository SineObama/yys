package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseBeforeActionBuff;
import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

/**
 * 持续伤害基类。
 */
public abstract class BaseDoT extends BaseBeforeActionBuff implements DoT {
    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     */
    public BaseDoT(int last, String name, Entity src) {
        super(last, name, src);
    }

    @Override
    protected final void doBeforeAction(DamageController controller, Entity self) {
        log.info(Msg.info(self, "受到持续伤害效果", getName()));
        double damage = handle(self);
        controller.directDamage(self, (int) damage);
    }
}
