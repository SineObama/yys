package com.sine.yys.skill.passive;

import com.sine.yys.event.CommonAttackEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

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
    public void init(Controller controller, Entity self) {
        controller.getCamp(self).getEventController().add(new Handler(self));
    }

    class Handler extends SealablePassiveHandler implements EventHandler<CommonAttackEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(CommonAttackEvent event) {
            if (event.getEntity() != self && RandUtil.success(getPct())) {  // 前者表示非自身的普攻事件
                log.info(Msg.trigger(self, XieZhan.this));
                event.getController().xieZhan(self, event.getTarget());
            }
        }
    }
}
