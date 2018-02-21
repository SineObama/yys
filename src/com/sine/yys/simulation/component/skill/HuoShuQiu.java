package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.buff.buff.LongShouZhiYuBuff;
import com.sine.yys.simulation.component.model.event.BeAttackEvent;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 辉夜姬-火鼠裘。
 */
public class HuoShuQiu extends BasePassiveSkill implements PassiveSkill {
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
    public void init(Controller controller) {
        Entity self = controller.getSelf();
        self.getEventController().add(new Handler(self, false));
        self.getCamp().getEventController().add(new Handler(self, true));
    }

    /**
     * 通过参数创建2个实例，分别监听自身被攻击和阵营被攻击。
     */
    class Handler extends SealablePassiveHandler implements EventHandler<BeAttackEvent> {
        private final boolean flag;

        Handler(Entity self, boolean huanJing) {
            super(self);
            this.flag = huanJing;
        }

        @Override
        public boolean sealed() {
            return super.sealed() || (self.getBuffController().getUnique(LongShouZhiYuBuff.class) == null ^ flag);
        }

        @Override
        public void handle(BeAttackEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(self, HuoShuQiu.this));
                self.getFireRepo().addFire(1);
            }
        }
    }
}
