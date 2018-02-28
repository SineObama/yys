package com.sine.yys.skill.commonattack;

import com.sine.yys.event.AfterMovementEvent;
import com.sine.yys.event.BeforeActionEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 花鸟卷-归鸟。
 */
public class GuiNiao extends BaseCommonAttack {
    public static final String FeiNiao = "FeiNiao";

    @Override
    public String getName() {
        return "归鸟";
    }

    @Override
    public int getTimes(Entity self) {
        return getFeiNiao(self);
    }

    @Override
    public double getCoefficient() {
        return 0.4;
    }

    public int getMaxFeiNiao() {
        return 3;
    }

    public double getCureLifePct() {
        return 0.1;
    }

    public double getCurePct() {
        return 0.8;
    }

    public int getFeiNiao(Entity self) {
        return self.get(GuiNiao.class, FeiNiao, 0);
    }

    /**
     * 包括上限检查。
     */
    protected void addFeiNiao(Entity self) {
        int feiNiao = getFeiNiao(self);
        if (feiNiao < getMaxFeiNiao()) {
            feiNiao += 1;
            self.put(GuiNiao.class, FeiNiao, feiNiao);
            log.info(Msg.info(self, "飞鸟数增加1"));
        }
        log.info(Msg.info(self, "当前剩余飞鸟数 " + feiNiao));
    }

    @Override
    public void init(Controller controller, Entity self) {
        self.put(GuiNiao.class, FeiNiao, 1);
        self.getEventController().add(new BeforeActionHandler(self));

        // XXXX 花鸟卷死后影响？还是采用移除监听器？
        controller.getCamp(self).getEventController().add(new AfterMovementHandler(self));
        controller.getCamp(self).getOpposite().getEventController().add(new AfterMovementHandler(self));
    }

    class BeforeActionHandler extends BaseHandler implements EventHandler<BeforeActionEvent> {
        protected BeforeActionHandler(Entity self) {
            super(self);
        }

        @Override
        public void handle(BeforeActionEvent event) {
            addFeiNiao(self);
        }
    }

    class AfterMovementHandler extends BaseHandler implements EventHandler<AfterMovementEvent> {
        protected AfterMovementHandler(Entity self) {
            super(self);
        }

        @Override
        public void handle(AfterMovementEvent event) {
            if (getFeiNiao(self) == 0)
                addFeiNiao(self);
        }
    }
}
