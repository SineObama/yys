package com.sine.yys.skill;

import com.sine.yys.event.UseFireEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

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
