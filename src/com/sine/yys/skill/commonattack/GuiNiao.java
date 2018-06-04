package com.sine.yys.skill.commonattack;

import com.sine.yys.event.AfterMovementEvent;
import com.sine.yys.event.BeforeRoundEvent;
import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.impl.AttackTypeImpl;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 花鸟卷-归鸟。
 */
public class GuiNiao extends BaseCommonAttack {
    public static final String FeiNiao = "FeiNiao";
    private final AfterMovementHandler afterMovementHandler = new AfterMovementHandler();

    @Override
    protected void doApply(Entity target) {
        super.doApply(target);
        cure();
    }

    protected void cure() {
        final Controller controller = getController();
        final Entity self = getSelf();
        for (int i = 0; i < getTimes(); i++)
            if (RandUtil.success(getCurePct()))
                controller.cure(self, getOwn().getLeastLifeShikigami(), getCureLifePct() * self.getMaxLife());
    }

    @Override
    public void doCounter(Entity target) {
        super.doCounter(target);
        cure();

        // 减少一只飞鸟
        int niao = getFeiNiao();
        if (niao > 1)
            getSelf().put(GuiNiao.FeiNiao, niao - 1);
    }

    @Override
    public String getName() {
        return "归鸟";
    }

    @Override
    public int getTimes() {
        return getFeiNiao();
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

    public int getFeiNiao() {
        return getSelf().get(FeiNiao, 0);
    }

    /**
     * 包括上限检查。
     */
    protected void addFeiNiao() {
        int feiNiao = getFeiNiao();
        if (feiNiao < getMaxFeiNiao()) {
            feiNiao += 1;
            getSelf().put(FeiNiao, feiNiao);
            log.info(Msg.info(getSelf(), "飞鸟数增加1"));
        }
        log.info(Msg.info(getSelf(), "当前剩余飞鸟数", feiNiao));
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.put(FeiNiao, 1);
        self.getEventController().add(new BeforeActionHandler());
    }

    @Override
    protected EventHandler<EnterEvent> getEnterHandler() {
        return event -> getOwn().getEventController().add(afterMovementHandler);
    }

    @Override
    protected EventHandler<DieEvent> getDieHandler() {
        return event -> getOwn().getEventController().remove(afterMovementHandler);
    }

    class BeforeActionHandler implements EventHandler<BeforeRoundEvent> {
        @Override
        public void handle(BeforeRoundEvent event) {
            addFeiNiao();
        }
    }

    class AfterMovementHandler implements EventHandler<AfterMovementEvent> {
        @Override
        public void handle(AfterMovementEvent event) {
            if (getFeiNiao() == 0)
                addFeiNiao();
        }
    }
}
