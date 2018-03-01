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
    private final BeAttackHandler ownBeAttackHandler = new BeAttackHandler(true);

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
        self.getEventController().add(new BeAttackHandler(false));
        getOwn().getEventController().add(ownBeAttackHandler);
    }

    @Override
    public void onDie() {
        getOwn().getEventController().remove(ownBeAttackHandler);
    }

    /**
     * 通过参数创建2个实例，分别监听自身被攻击和阵营被攻击。
     */
    class BeAttackHandler extends SealablePassiveHandler implements EventHandler<BeAttackEvent> {
        private final boolean flag;

        BeAttackHandler(boolean huanJing) {
            this.flag = huanJing;
        }

        @Override
        public boolean sealed() {
            return super.sealed() || (getSelf().getBuffController().get(LongShouZhiYuBuff.class) == null ^ flag);
        }

        @Override
        public void handle(BeAttackEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), HuoShuQiu.this));
                getSelf().getFireRepo().addFire(1);
            }
        }
    }
}
