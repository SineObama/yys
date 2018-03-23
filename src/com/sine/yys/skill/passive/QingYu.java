package com.sine.yys.skill.passive;

import com.sine.yys.event.*;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.skill.model.QingTianWaWa;
import com.sine.yys.util.Msg;

/**
 * 日和坊-晴雨。
 * <p>
 * 鉴于自身存活且晴天娃娃存活才拥有被动效果，监听器随自身存亡添加移除，内部再判断晴天娃娃。
 */
public class QingYu extends BasePassiveSkill implements EventHandler<AfterRoundEvent> {
    private final BeDamageHandler beDamageHandler = new BeDamageHandler();
    private final BeCureHandler beCureHandler = new BeCureHandler();

    @Override
    public String getName() {
        return "晴雨";
    }

    /**
     * 友方单位损失生命的转换比例。
     */
    public double getLostLifeCoefficient() {
        return 0.25;
    }

    /**
     * 敌方单位获得治疗的转换比例。
     */
    public double getEnemyCureCoefficient() {
        return 0.2;
    }

    /**
     * 最多治疗目标已损失生命比例。
     */
    public double getMaxCureLostPct() {
        return 0.3;
    }

    @Override
    protected EventHandler<EnterEvent> getEnterHandler() {
        return event -> {
            getEnemy().getEventController().add(this);
            getEnemy().getEventController().add(beCureHandler);
            getOwn().getEventController().add(beDamageHandler);
        };
    }

    public void addEnergy(int count) {
        final QingTianWaWa waWa = getSelf().get(QingTianWaWa.class, null);
        if (waWa.getCd() == 0)
            waWa.add(count);
    }

    @Override
    public EventHandler<DieEvent> getDieHandler() {
        return event -> {
            getEnemy().getEventController().remove(this);
            getEnemy().getEventController().remove(beCureHandler);
            getOwn().getEventController().remove(beDamageHandler);
        };
    }

    @Override
    public void handle(AfterRoundEvent event) {
        final Entity self = getSelf();
        final QingTianWaWa waWa = self.get(QingTianWaWa.class, null);
        if (waWa.getCd() > 0)
            return;
        final Integer energy = waWa.getEnergy();
        if (energy == 0)
            return;
        log.info(Msg.trigger(self, this));
        final ShikigamiEntity entity = getOwn().getLeastLifeShikigami();
        int count = (int) (entity.getLostLifeInt() * getMaxCureLostPct());
        count = waWa.use(count);
        getController().cure(self, entity, count);
    }

    class BeDamageHandler extends SealablePassiveHandler implements EventHandler<LostLifeEvent> {
        @Override
        public void handle(LostLifeEvent event) {
            addEnergy((int) (event.getCount() * getLostLifeCoefficient()));
        }
    }

    class BeCureHandler extends SealablePassiveHandler implements EventHandler<BeCureEvent> {
        @Override
        public void handle(BeCureEvent event) {
            addEnergy((int) (event.getCount() * getEnemyCureCoefficient()));
        }
    }
}
