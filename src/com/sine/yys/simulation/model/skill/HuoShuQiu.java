package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.event.BeAttackEvent;
import com.sine.yys.simulation.model.event.LongShouZhiYuOff;
import com.sine.yys.simulation.model.event.LongShouZhiYuOn;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 辉夜姬-火鼠裘。
 */
public class HuoShuQiu extends BaseSkill implements PassiveSkill, EventHandler<BeAttackEvent> {
    @Override
    public String getName() {
        return "火鼠裘";
    }

    /**
     * 回火概率。
     */
    public double getPct() {
        return 0.4;
    }

    @Override
    public void handle(BeAttackEvent event) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            getSelf().getFireRepo().addFire(1);
        }
    }

    @Override
    protected void doInit(InitContext context) {
        getSelf().getEventController().add(LongShouZhiYuOn.class, event -> {
            getSelf().getEventController().remove(HuoShuQiu.this);
            getSelf().getCamp().getEventController().add(HuoShuQiu.this);
        });
        getSelf().getEventController().add(LongShouZhiYuOff.class, event -> {
            getSelf().getEventController().add(HuoShuQiu.this);
            getSelf().getCamp().getEventController().remove(HuoShuQiu.this);
        });
    }
}
