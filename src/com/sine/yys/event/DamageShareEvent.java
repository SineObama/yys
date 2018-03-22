package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

import java.util.logging.Logger;

/**
 * 伤害分摊事件。
 * <p>
 * 监听器进行伤害分摊，设置剩余伤害（给原本的式神）。
 * 在真正造成伤害前触发（破盾后）。
 * <p>
 * 用途：
 * 1. 薙魂；
 * 2. 金鱼姬的金鱼；
 * 3. 涓流；
 * 4. 未来：小松丸躲避。
 */
public class DamageShareEvent extends BaseAttackEvent {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final double total;
    private double left;
    private boolean set;

    public DamageShareEvent(Entity src, Entity entity, double total, AttackType type) {
        super(src, entity, type);
        this.total = total;
        this.left = total;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        if (this.set) {
            log.warning("重复设置伤害分摊，可能有多个效果。");
            return;
        }
        this.set = true;
        this.left = left;
    }

    public double getTotal() {
        return total;
    }
}
