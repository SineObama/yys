package com.sine.yys.skill.passive;

import com.sine.yys.event.BeAttackEvent;
import com.sine.yys.event.LongShouZhiYuOff;
import com.sine.yys.event.LongShouZhiYuOn;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 辉夜姬-火鼠裘。
 */
public class HuoShuQiu extends BasePassiveSkill implements PassiveSkill {
    private final BeAttackHandler beAttackHandler = new BeAttackHandler();

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
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(beAttackHandler);
        self.getEventController().add(LongShouZhiYuOn.class, event -> {
            self.getEventController().remove(beAttackHandler);
            getOwn().getEventController().add(beAttackHandler);
        });
        self.getEventController().add(LongShouZhiYuOff.class, event -> {
            getOwn().getEventController().remove(beAttackHandler);
            self.getEventController().add(beAttackHandler);
        });
    }

    class BeAttackHandler extends SealablePassiveHandler implements EventHandler<BeAttackEvent> {
        @Override
        public void handle(BeAttackEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), HuoShuQiu.this));
                getSelf().getFireRepo().addFire(1);
            }
        }
    }
}
