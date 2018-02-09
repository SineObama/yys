package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.CommonAttackEvent;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.component.event.UseFireEvent;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 协战
 */
public class XieZhan extends BaseSkill implements PassiveSkill, EventHandler<CommonAttackEvent> {
    public XieZhan(Entity self) {
        super(self);
    }

    @Override
    public String getName() {
        return "协战";
    }

    public double pct() {
        return 0.3;
    }

    @Override
    public void handle(CommonAttackEvent event, Controller controller) {
        // TODO 混乱状态下打自己人，协战随机敌方目标
        if (RandUtil.success(pct())) {
            log.info(Msg.trigger(this));
//            self.
//            controller.damage(event.getTarget());
        }
    }

    @Override
    public void init(InitContext context) {
        context.getOwn().getEventController().add(CommonAttackEvent.class, this);
    }
}
