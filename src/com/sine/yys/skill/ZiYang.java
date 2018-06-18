package com.sine.yys.skill;

import com.sine.yys.event.DieEvent;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.skill.model.QingTianWaWa;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.Collections;
import java.util.List;

/**
 * 日和坊-滋养。
 * <p>
 * 关于复活有几个设定不清醒（牛角尖）：
 * 1. 在同一次行动中，日和坊先死应该不会复活，但是后死好像会？
 * 2. 混乱（还可有最后1回合封被动）打死自己人之后会不会复活？
 */
public class ZiYang extends BaseActiveSkill {
    private final DieHandler dieHandler = new DieHandler();

    @Override
    protected void doApply(Entity target) {
        QingTianWaWa waWa = getSelf().get(QingTianWaWa.class, null);
        waWa.add((int) (getSelf().getMaxLife() * getAddEnergyByLifePct()));
    }

    @Override
    public int getFire() {
        return 2;
    }

    /**
     * @return 增加等同于日和坊生命上限比例的日光能量。
     */
    public double getAddEnergyByLifePct() {
        return 0.4;
    }

    /**
     * @return 晴天娃娃牺牲时，日光能量转换为治疗的比例。
     */
    public double getCurePctOfEnergy() {
        return 1.0;
    }

    @Override
    public TargetResolver getTargetResolver() {
        return (own, self) -> {
            QingTianWaWa waWa = getSelf().get(QingTianWaWa.class, null);
            return waWa.getCd() > 0 ? null : Collections.emptyList();
        };
    }

    /**
     * @return 晴天娃娃复活所需回合数。
     */
    public int getReviveCD() {
        return 3;
    }

    @Override
    protected void doAfterAction() {
        QingTianWaWa waWa = getSelf().get(QingTianWaWa.class, null);
        waWa.step();
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.put(QingTianWaWa.class, new QingTianWaWa(getReviveCD(), self));
    }

    @Override
    protected void onEnter() {
        getOwn().getEventController().add(dieHandler);
    }

    @Override
    protected void onDie() {
        // XXXX 死亡是否需要处理日光能量
        getOwn().getEventController().remove(dieHandler);
    }

    @Override
    public String getName() {
        return "滋养";
    }

    class DieHandler extends SealablePassiveHandler implements EventHandler<DieEvent> {
        private ShikigamiEntity choosed;
        private final Callback callback = () -> {
            final Entity self = getSelf();
            if (self.isDead())
                return;
            if (choosed == null)
                return;
            log.info(Msg.trigger(self, ZiYang.this));
            QingTianWaWa waWa = self.get(QingTianWaWa.class, null);
            waWa.sacrifice();
            getController().revive(choosed, Integer.MAX_VALUE);  // 回复满生命
            double energy = waWa.use(Integer.MAX_VALUE);  // 消耗所有能量。
            energy *= getCurePctOfEnergy();  // 总治疗量
            final List<? extends ShikigamiEntity> allShikigami = getOwn().getAllShikigami();
            final int num = allShikigami.size() - 1;  // 其余队友数量
            energy /= num;  // 每人治疗量
            for (ShikigamiEntity shikigamiEntity : allShikigami)
                if (shikigamiEntity != choosed)
                    getController().cure(self, shikigamiEntity, energy);
        };

        @Override
        public void handle(DieEvent event) {
            if (event.getEntity() == getSelf()) {
                log.warning("日和坊自己死后仍能进入死亡处理");
                return;
            }
            QingTianWaWa waWa = getSelf().get(QingTianWaWa.class, null);
            if (waWa.getCd() > 0)
                return;
            // 查找己方式神站位，可复活的队友。
            choosed = RandUtil.choose(getOwn().getRevivable());
            getController().addAction(400, callback);
        }
    }
}
