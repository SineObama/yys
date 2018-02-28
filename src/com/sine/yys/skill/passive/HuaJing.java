package com.sine.yys.skill.passive;

import com.sine.yys.event.AfterActionEvent;
import com.sine.yys.event.BeforeControlEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.skill.commonattack.GuiNiao;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 花鸟卷-画境。
 */
public class HuaJing extends BasePassiveSkill implements PassiveSkill {
    @Override
    public String getName() {
        return "画境";
    }

    /**
     * @return 进入画卷概率。
     */
    public double getPct() {
        return 0.3;
    }

    /**
     * @return 抵销控制概率。
     */
    public double getCancelPct() {
        return 0.3;
    }

    public double getAddPosition() {
        return 0.3;
    }

    @Override
    public void init(Controller controller, Entity self) {
        self.getEventController().add(new Handler(self));
        controller.getCamp(self).getEventController().add(new HandleControlBuff(self));
    }

    class Handler extends SealablePassiveHandler implements EventHandler<AfterActionEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(AfterActionEvent event) {
            if (!self.isDead() && RandUtil.success(getPct())) {  // XXX 被反弹死会不会触发被动？看着好麻烦
                log.info(Msg.trigger(self, HuaJing.this));
                self.addPosition(getAddPosition());
            }
        }
    }

    class HandleControlBuff extends SealablePassiveHandler implements EventHandler<BeforeControlEvent> {
        HandleControlBuff(Entity self) {
            super(self);
        }

        @Override
        public void handle(BeforeControlEvent event) {
            final Integer niao = self.get(GuiNiao.class, GuiNiao.FeiNiao, 0);
            if (!self.isDead() && niao > 0 && RandUtil.success(getCancelPct())) {  // XXX 被反弹死会不会触发被动？看着好麻烦
                log.info(Msg.trigger(self, HuaJing.this));
                log.info(Msg.info(self, "牺牲飞鸟抵销了 " + event.getControlBuff().getName()));
                log.info(Msg.info(self, "当前剩余飞鸟数 " + (niao - 1)));
                self.put(GuiNiao.class, GuiNiao.FeiNiao, niao - 1);
                event.setEffective(false);
            }
        }
    }
}
