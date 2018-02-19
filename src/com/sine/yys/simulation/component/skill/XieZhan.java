package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.InitContext;
import com.sine.yys.simulation.component.model.event.CommonAttackEvent;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 姑获鸟-协战。
 */
public class XieZhan extends BaseSkill implements PassiveSkill, EventHandler<CommonAttackEvent> {
    @Override
    public String getName() {
        return "协战";
    }

    public double getPct() {
        return 0.3;
    }

    @Override
    public void handle(CommonAttackEvent event) {
        if (event.getSelf() != getSelf() && RandUtil.success(getPct())) {  // 前者表示非自身的普攻事件
            log.info(Msg.trigger(getSelf(), this));
            getSelf().xieZhan(event.getTarget());
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getOwn().getEventController().add(this);
    }
}
