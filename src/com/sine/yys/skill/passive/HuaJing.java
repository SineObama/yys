package com.sine.yys.skill.passive;

import com.sine.yys.event.AfterRoundEvent;
import com.sine.yys.event.BeforeControlEvent;
import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.skill.commonattack.GuiNiao;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 花鸟卷-画境。
 */
public class HuaJing extends BasePassiveSkill implements EventHandler<BeforeControlEvent>, PctEffect {
    @Override
    public String getName() {
        return "画境";
    }

    /**
     * @return 进入画卷概率。
     */
    @Override
    public double getPct() {
        return 0.3;
    }

    /**
     * @return 抵销控制概率。
     */
    public double getCancelPct() {
        return 0.3;
    }

    /**
     * @return 增加行动条。
     */
    public double getAddPosition() {
        return 0.3;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(new AfterActionHandler());
    }

    @Override
    protected EventHandler<EnterEvent> getEnterHandler() {
        return event -> getOwn().getEventController().add(this);
    }

    @Override
    public EventHandler<DieEvent> getDieHandler() {
        return event -> getOwn().getEventController().remove(this);
    }

    @Override
    public void handle(BeforeControlEvent event) {
        if (event.isNotEffective())
            return;
        final Entity self = getSelf();
        final Integer niao = self.get(GuiNiao.FeiNiao, 0);
        if (niao > 0 && RandUtil.success(getCancelPct())) {
            log.info(Msg.trigger(self, HuaJing.this));
            log.info(Msg.info(self, "牺牲飞鸟抵消了", event.getControlBuff().getName()));
            log.info(Msg.info(self, "当前剩余飞鸟数", (niao - 1)));
            self.put(GuiNiao.FeiNiao, niao - 1);
            event.setEffective(false);
        }
    }

    class AfterActionHandler extends SealablePassiveHandler implements EventHandler<AfterRoundEvent> {
        @Override
        public void handle(AfterRoundEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), HuaJing.this));
                getSelf().addPosition(getAddPosition());
            }
        }
    }
}
