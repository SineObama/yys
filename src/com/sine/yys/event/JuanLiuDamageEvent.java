package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 伤害被涓流分摊到其他目标的事件。
 * <p>
 * 霜天之织专用。
 * <p>
 * 没有发现类似的效果需求，没有想到更好的方法。
 * 注意必须是直接伤害被分摊，薙魂后被涓流不算。
 */
public class JuanLiuDamageEvent extends BaseEntityEvent {
    public JuanLiuDamageEvent(Entity entity) {
        super(entity);
    }
}
