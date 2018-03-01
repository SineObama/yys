package com.sine.yys.skill.passive;

import com.sine.yys.event.UseFireEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 青行灯-明灯。
 * TODO 2018-2-28游戏更新。
 */
public class MingDeng extends BasePassiveSkill implements PassiveSkill {

    private final UseFireHandler useFireHandler = new UseFireHandler();

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
    public void doInit(Controller controller, Entity self) {
        getOwn().getEventController().add(useFireHandler);
    }

    @Override
    public void onDie() {
        getOwn().getEventController().remove(useFireHandler);
    }

    class UseFireHandler extends SealablePassiveHandler implements EventHandler<UseFireEvent> {
        @Override
        public void handle(UseFireEvent event) {
            if (event.getEntity() != getSelf() && RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), MingDeng.this));
                event.setCostFire(0);
            }
        }
    }
}
