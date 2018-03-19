package com.sine.yys.event;

import com.sine.yys.inter.Entity;

import java.util.logging.Logger;

/**
 * 伤害分摊事件。
 * 在真正造成伤害前触发（破盾后）。
 * 监听器需要进行伤害分摊后的施加，并设置剩余伤害（给原本的式神）。
 * <p>
 * 包括薙魂、金鱼姬的金鱼。
 * 未来可能实现椒图、小松丸躲避。
 */
public class DamageShareEvent extends BaseVectorEvent {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final double total;
    private double left;
    private boolean set;

    public DamageShareEvent(Entity src, Entity entity, double total) {
        super(src, entity);
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
