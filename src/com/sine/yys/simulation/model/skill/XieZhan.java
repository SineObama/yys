package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.CommonAttackEvent;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 姑获鸟-协战。
 */
public class XieZhan extends BaseSkill implements PassiveSkill, EventHandler<CommonAttackEvent> {
    @Override
    public String getName() {
        return "协战";
    }

    public double getPct() {
        return 0.3;
    }

    @Override
    public void handle(CommonAttackEvent event, Controller controller) {
        // TODO 混乱状态下打自己人，协战随机敌方目标
        if (event.getSelf() != getSelf() && RandUtil.success(getPct())) {
            log.info(Msg.trigger(this));
            Entity target = event.getTarget();
            final CommonAttack commonAttack = getSelf().getCommonAttack();
            // 目标死亡则随机攻击另一个目标
            if (!target.isAlive()) {
                target = controller.getEnemy().randomTarget();
            }
            if (target != null)
                commonAttack.xieZhan(target, controller);
        }
    }

    @Override
    public void init(InitContext context) {
        super.init(context);
        context.getOwn().getEventController().add(CommonAttackEvent.class, this);
    }
}
