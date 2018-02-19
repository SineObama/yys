package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.BeDamageEvent;
import com.sine.yys.simulation.component.model.event.BeforeActionEvent;
import com.sine.yys.simulation.info.AttackInfoImpl;
import com.sine.yys.simulation.util.Msg;

/**
 * 彼岸花-赤团华。
 */
public class ChiTuanHua extends BasePassiveSkill implements PassiveSkill, EventHandler<BeforeActionEvent> {
    private XueZhiHuaHai xueZhiHuaHai;

    @Override
    public String getName() {
        return "赤团华";
    }

    public double getCoefficient() {
        return 0.39 * 1.25;
    }

    @Override
    public void handle(BeforeActionEvent event) {
        int level = xueZhiHuaHai.getLevel();
        if (!getSelf().isDead() && level > 0) {
            log.info(Msg.trigger(getSelf(), this));
            getSelf().attack(event.getTarget(), new AttackInfoImpl(getCoefficient() * level));
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getEnemy().getEventController().add(this);
        for (ActiveSkill activeSkill : getSelf().getActiveSkills()) {
            if (activeSkill instanceof XueZhiHuaHai) {
                xueZhiHuaHai = (XueZhiHuaHai) activeSkill;
                break;
            }
        }
        if (xueZhiHuaHai == null)
            throw new RuntimeException("找不到血之花海技能");
        ChiTuanHua self = this;
        getSelf().getEventController().add(new EventHandler<BeDamageEvent>() {
            @Override
            public void handle(BeDamageEvent event) {
                int src = (int) (event.getSrc() * 4);
                int dst = (int) (event.getDst() * 4);
                if (src != 4 && src != dst) {
                    log.info(Msg.trigger(getSelf(), self));
                    xueZhiHuaHai.addLevel(src - dst);
                    xueZhiHuaHai.addShield();
                }
            }
        });
    }
}
