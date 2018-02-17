package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.event.BeCriticalEvent;
import com.sine.yys.simulation.model.shield.DiZangXiangShield;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 地藏像。
 */
public class DiZangXiang extends BaseMitama implements Mitama, EventHandler<BeCriticalEvent> {
    @Override
    public String getName() {
        return "地藏像";
    }

    /**
     * 为其他友方单位加盾的概率。
     */
    public double getPct() {
        return 0.3;
    }

    /**
     * 生成一个能够吸收生命值***的伤害的护盾。
     */
    public double getShieldByMaxLife() {
        return 0.1;
    }

    @Override
    public void handle(BeCriticalEvent event) {
        log.info(Msg.trigger(this));
        final int value = (int) (getSelf().getMaxLife() * getShieldByMaxLife());
        getSelf().getBuffController().addShield(new DiZangXiangShield(value));
        for (Entity entity : getSelf().getCamp().getAllAlive()) {
            if (entity == getSelf())
                continue;
            if (RandUtil.success(getPct())) {
                final DiZangXiangShield diZangXiangShield = new DiZangXiangShield(value);
                log.info(Msg.addBuff(getSelf(), entity, diZangXiangShield));
                entity.getBuffController().addShield(diZangXiangShield);
            }
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getSelf().getEventController().add(this);
    }
}
