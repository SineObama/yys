package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 行动前事件。
 * <p>
 * 包括赤团华、龙首之玉、归鸟。
 * 未来还有鲤鱼旗、樱花妖、招财猫……
 */
public class BeforeActionEvent extends BaseEntityEvent implements Event {
    public BeforeActionEvent(Entity entity) {
        super(entity);
    }
}
