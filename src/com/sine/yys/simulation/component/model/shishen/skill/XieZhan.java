package com.sine.yys.simulation.component.model.shishen.skill;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;
import com.sine.yys.simulation.component.model.event.CommonAttackEvent;
import com.sine.yys.simulation.event.EventHandler;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 姑获鸟-协战。
 */
public class XieZhan extends BasePassiveSkill implements PassiveSkill {
    @Override
    public String getName() {
        return "协战";
    }

    public double getPct() {
        return 0.3;
    }

    @Override
    public void init(Controller controller) {
        controller.getOwn().getEventController().add(new Handler(controller.getSelf()));
    }

    class Handler extends SealablePassiveHandler implements EventHandler<CommonAttackEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(CommonAttackEvent event) {
            if (event.getEntity() != self && RandUtil.success(getPct())) {  // 前者表示非自身的普攻事件
                log.info(Msg.trigger(self, XieZhan.this));
                event.getController().xieZhan(event.getTarget());
            }
        }
    }
}
