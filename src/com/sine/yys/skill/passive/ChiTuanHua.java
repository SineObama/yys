package com.sine.yys.skill.passive;

import com.sine.yys.event.BeDamageEvent;
import com.sine.yys.event.BeforeActionEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.skill.XueZhiHuaHai;
import com.sine.yys.skill.model.AttackInfoImpl;
import com.sine.yys.util.Msg;

/**
 * 彼岸花-赤团华。
 */
public class ChiTuanHua extends BasePassiveSkill implements PassiveSkill, EventHandler<BeforeActionEvent> {
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
        getEnemy().getEventController().add(this);
        self.getEventController().add(new BeDamageHandler());
    }

    @Override
    public void onDie() {
        getEnemy().getEventController().remove(this);
    }

    @Override
    public void handle(BeforeActionEvent event) {
        int level = xueZhiHuaHai.getLevel();
        if (level > 0) {
            log.info(Msg.trigger(getSelf(), ChiTuanHua.this));
            getController().attack(getSelf(), event.getEntity(), new AttackInfoImpl(getCoefficient() * level), null);
        }
    }

    class BeDamageHandler extends SealablePassiveHandler implements EventHandler<BeDamageEvent> {
        @Override
        public void handle(BeDamageEvent event) {
            int src = (int) (event.getSrc() * 4);
            int dst = (int) (event.getDst() * 4);
            if (src != 4 && src != dst) {
                log.info(Msg.trigger(getSelf(), ChiTuanHua.this));
                xueZhiHuaHai.addLevel(src - dst);
                xueZhiHuaHai.addShield();
            }
        }
    }
}
