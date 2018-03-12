package com.sine.yys.skill;

import com.sine.yys.event.DieEvent;
import com.sine.yys.inter.*;
import com.sine.yys.skill.model.QingTianWaWa;
import com.sine.yys.util.RandUtil;

import java.util.Collections;

/**
 * 日和坊-滋养。
 * <p>
 * 关于复活有几个设定不清醒（牛角尖）：
 * 1. 在同一次行动中，日和坊先死应该不会复活，但是后死好像会？
 * 2. 混乱（还可有最后1回合封被动）打死自己人之后会不会复活？
 */
public class ZiYang extends BaseActiveSkill implements ActiveSkill {

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
     * @return 日光能量转换为治疗的比例。
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
        getOwn().getEventController().add(dieHandler);
    }

    @Override
    public void onDie() {
        // XXXX 是否需要处理日光能量
        getOwn().getEventController().remove(dieHandler);
    }

    @Override
    public String getName() {
        return "滋养";
    }

    class DieHandler extends SealablePassiveHandler implements EventHandler<DieEvent> {
        @Override
        public void handle(DieEvent event) {
            QingTianWaWa waWa = getSelf().get(QingTianWaWa.class, null);
            if (waWa.getCd() > 0)
                return;
            getController().addAction(400, () -> {
                // 查找己方式神站位，可复活的队友。
                final ShikigamiEntity choosed = RandUtil.choose(getOwn().getRevivable());
                if (choosed == null)
                    return;
                waWa.sacrifice();
            });
        }
    }
}
