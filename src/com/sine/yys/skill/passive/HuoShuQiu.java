package com.sine.yys.skill.passive;

import com.sine.yys.buff.buff.LongShouZhiYuBuff;
import com.sine.yys.event.BeAttackEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

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
        controller.getCamp(self).getEventController().add(new Handler(self, true));
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
