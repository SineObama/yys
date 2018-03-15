package com.sine.yys.skill.passive;

import com.sine.yys.event.CommonAttackEvent;
import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 姑获鸟-协战。
 */
public class XieZhan extends BasePassiveSkill implements PassiveSkill {

    private final CommonAttackHandler commonAttackHandler = new CommonAttackHandler();

    @Override
    public String getName() {
        return "协战";
    }

    public double getPct() {
        return 0.3;
    }

    @Override
    protected EventHandler<EnterEvent> getEnterHandler() {
        return event -> getOwn().getEventController().add(commonAttackHandler);
    }

    @Override
    public EventHandler<DieEvent> getDieHandler() {
        return event -> getOwn().getEventController().remove(commonAttackHandler);
    }

    class CommonAttackHandler extends SealablePassiveHandler implements EventHandler<CommonAttackEvent> {
        @Override
        public void handle(CommonAttackEvent event) {
            final Entity self = getSelf();
            if (event.getEntity() != self && RandUtil.success(getPct())) {  // 前者表示非自身的普攻事件
                log.info(Msg.trigger(self, XieZhan.this));
                getController().xieZhan(self, event.getTarget());
            }
        }
    }
}
