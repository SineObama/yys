package com.sine.yys.mitama;

import com.sine.yys.event.PreCureEvent;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 树妖。
 * <p>
 * 按照网上看到的，增减疗直接相加。
 */
public class ShuYao extends BaseSelfMitama implements EventHandler<PreCureEvent> {
    public double getAddCure() {
        return 0.2;
    }

    @Override
    public String getName() {
        return "树妖";
    }

    @Override
    public void handle(PreCureEvent event) {
        log.info(Msg.trigger(getSelf(), this));
        event.setCure(getAddCure());
    }
}
