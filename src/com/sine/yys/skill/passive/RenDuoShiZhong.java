package com.sine.yys.skill.passive;

import com.sine.yys.event.FinishActionEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 镰鼬-人多势众。
 * <p>
 * 行动结束时有概率获得1次额外行动机会（将自身行动条置为1）。
 * <p>
 * 规则：
 * 1. {@linkplain com.sine.yys.buff.control.Unmovable 无法行动控制}下不会触发；
 * 2. 触发后能触发轮入道，轮入道触发后不能触发。
 * 3. 触发后拉条效果消失（拉条后行动条增加30%，行动后行动条在0.3位置，但拉条后触发，若不再拉条，行动条重置为0）。
 * 4. 触发后的行动不会推进鬼火条。
 * <p>
 * 以前在狰触发后可以触发（好像改掉了）。
 */
public class RenDuoShiZhong extends BasePassiveSkill implements EventHandler<FinishActionEvent>, PctEffect {
    @Override
    public String getName() {
        return "人多势众";
    }

    /**
     * 再次行动概率。
     */
    @Override
    public double getPct() {
        return 0.3;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    @Override
    public void handle(FinishActionEvent event) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), RenDuoShiZhong.this));
            getController().actionChance(getSelf());
        }
    }
}
