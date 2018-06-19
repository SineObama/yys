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
 * * 薙魂；
 * * 金鱼姬的金鱼；
 * * 涓流；
 * * 小松丸躲避；
 */
public class DamageShareEvent extends BaseAttackEvent {
    private final Logger log = Logger.getLogger(getClass().getName());
    private boolean set = false;

    public DamageShareEvent(Entity src, Entity entity, AttackType type) {
        super(src, entity, type);
    }

    public void setLeft(double left) {
        if (this.set) {
            log.warning("重复设置伤害分摊，可能有多个效果。");
            return;
        }
        this.set = true;
        getType().setDamage(left);
    }
}
