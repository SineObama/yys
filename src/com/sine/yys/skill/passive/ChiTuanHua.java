package com.sine.yys.skill.passive;

import com.sine.yys.event.BeforeRoundEvent;
import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.event.LostLifeEvent;
import com.sine.yys.impl.OriginAttackType;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.skill.XueZhiHuaHai;
import com.sine.yys.skill.model.AttackInfoImpl;
import com.sine.yys.util.Msg;

/**
 * 彼岸花-赤团华。
 */
public class ChiTuanHua extends BasePassiveSkill implements EventHandler<BeforeRoundEvent> {
    private final XueZhiHuaHai xueZhiHuaHai;

    public ChiTuanHua(XueZhiHuaHai xueZhiHuaHai) {
        this.xueZhiHuaHai = xueZhiHuaHai;
    }

    @Override
    public String getName() {
        return "赤团华";
    }

    public double getCoefficient() {
        return 0.39 * 1.25;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(new BeDamageHandler());
    }

    @Override
    protected EventHandler<EnterEvent> getEnterHandler() {
        return event -> getEnemy().getEventController().add(this, 400);
    }

    @Override
    public EventHandler<DieEvent> getDieHandler() {
        return event -> getEnemy().getEventController().remove(this);
    }

    @Override
    public void handle(BeforeRoundEvent event) {
        int level = xueZhiHuaHai.getLevel();
        if (level > 0) {
            log.info(Msg.trigger(getSelf(), ChiTuanHua.this));
            getController().attack(getSelf(), event.getEntity(), new OriginAttackType(getSelf(), event.getEntity(), new AttackInfoImpl(getCoefficient() * level, 0.0)), null);
        }
    }

    class BeDamageHandler extends SealablePassiveHandler implements EventHandler<LostLifeEvent> {
        @Override
        public void handle(LostLifeEvent event) {
            final int src = xueZhiHuaHai.getMaxLevel(event.getSrc());
            final int dst = xueZhiHuaHai.getMaxLevel(event.getDst());
            if (src != dst) {
                log.info(Msg.trigger(getSelf(), ChiTuanHua.this));
                xueZhiHuaHai.addLevel(dst - src);
                xueZhiHuaHai.addShield();
            }
        }
    }
}
