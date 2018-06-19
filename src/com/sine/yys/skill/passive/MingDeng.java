package com.sine.yys.skill.passive;

import com.sine.yys.event.UseFireEvent;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;
//TODO 2018-2-28游戏更新。

/**
 * 青行灯-明灯。
 */
public class MingDeng extends BasePassiveSkill implements EventHandler<UseFireEvent>, PctEffect {
    @Override
    public String getName() {
        return "明灯";
    }

    /**
     * 队友技能不耗火概率。
     */
    @Override
    public double getPct() {
        return 0.14;
    }

    @Override
    protected void onEnter() {
        getOwn().getEventController().add(this);
    }

    @Override
    protected void onDie() {
        getOwn().getEventController().remove(this);
    }

    @Override
    public void handle(UseFireEvent event) {
        if (event.getEntity() != getSelf() && RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), MingDeng.this));
            event.setCostFire(0);
        }
    }
}
