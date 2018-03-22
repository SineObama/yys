package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 行动前事件。
 * <p>
 * 用途：
 * 1. 赤团华、龙首之玉、归鸟；
 * 2. 未来还有鲤鱼旗、樱花妖……
 */
public class BeforeActionEvent extends BaseEntityEvent {
    public BeforeActionEvent(Entity entity) {
        super(entity);
    }
}
