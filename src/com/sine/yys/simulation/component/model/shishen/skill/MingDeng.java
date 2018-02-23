package com.sine.yys.simulation.component.model.shishen.skill;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;
import com.sine.yys.simulation.component.model.event.UseFireEvent;
import com.sine.yys.simulation.event.EventHandler;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 青行灯-明灯。
 */
public class MingDeng extends BasePassiveSkill implements PassiveSkill {
    @Override
    public String getName() {
        return "明灯";
    }

    /**
     * 队友技能不耗火概率。
     */
    public double getPct() {
        return 0.14;
    }

    @Override
    public void init(Controller controller) {
        controller.getOwn().getEventController().add(new Handler(controller.getSelf()));
    }

    class Handler extends SealablePassiveHandler implements EventHandler<UseFireEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(UseFireEvent event) {
            if (event.getEntity() != self && RandUtil.success(getPct())) {
                log.info(Msg.trigger(self, MingDeng.this));
                event.setCostFire(0);
            }
        }
    }
}
