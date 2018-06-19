package com.sine.yys.attacktype;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;
import com.sine.yys.rule.CalcDam;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.logging.Logger;

/**
 * 原始攻击。由式神直接打出的攻击，区别于被分摊的攻击、草人、针女等其他所有类型。
 * <p>
 * 伤害由双方属性和 AttackInfo 计算出初步结果。
 */
public class OriginAttackType extends AttackTypeImpl implements AttackType {
    final Entity self, target;
    final AttackInfo attackInfo;
    private final Logger log = Logger.getLogger(getClass().getName());
    boolean critical;

    public OriginAttackType(Entity self, Entity target, AttackInfo attackInfo) {
        this.self = self;
        this.target = target;
        this.attackInfo = attackInfo;
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
}
