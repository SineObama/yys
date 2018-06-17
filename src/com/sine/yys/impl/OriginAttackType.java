package com.sine.yys.impl;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.rule.CalcDam;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * 原始攻击，由攻击者和被攻击者双方属性计算出初步伤害。
 */
public class OriginAttackType extends AttackTypeImpl implements AttackType {
    private final Logger log = Logger.getLogger(getClass().getName());
    final Entity self, target;
    final AttackInfo attackInfo;
    boolean critical;
    private final Collection<DebuffEffect> debuffEffects;

    public OriginAttackType(Entity self, Entity target, AttackInfo attackInfo, Collection<DebuffEffect> debuffEffects) {
        this.self = self;
        this.target = target;
        this.attackInfo = attackInfo;
        if (debuffEffects != null)
            this.debuffEffects = debuffEffects;
        else
            this.debuffEffects = Collections.EMPTY_LIST;
    }

    public void setCounter() {
        counter = true;
    }

    @Override
    public double getDamage() {
        if (damage == null) {
            // 1.
            critical = RandUtil.success(self.getCritical());
            if (critical)
                log.info(Msg.info(self, "暴击"));
            damage = CalcDam.expect(self, target, attackInfo, critical);
            damage *= attackInfo.randomFloat();

            // 2.
            damage *= self.getDamageCoefficient();
            damage *= target.getBeDamageCoefficient();
        }
        return damage;
    }

    @Override
    public boolean isCritical() {
        return critical;
    }

    @Override
    public Collection<DebuffEffect> getDebuffEffects() {
        return debuffEffects;
    }
}
