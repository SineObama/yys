package com.sine.yys.skill.passive;

import com.sine.yys.event.FinishActionEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.RandUtil;

/**
 * 镰鼬-人多势众。
 * 行动结束时有概率获得1次额外行动机会。
 * 不能行动的控制效果（冰冻、眩晕、变形）下不会触发，能行动的控制效果（混乱、嘲讽、沉默）下可以触发。
 * 触发后能触发轮入道，轮入道触发后不能触发。
 * 在狰触发后可以触发（好像改掉了）。
 * 触发后拉条效果消失（拉条后行动条增加30%，行动后行动条在0.3位置，但拉条后触发，若不再拉条，行动条重置为0）。
 * 触发后的行动不会推进鬼火条。
 * <p>
 * 定义为“行动”后判定，将自身行动条置为1。
 */
public class RenDuoShiZhong extends BasePassiveSkill implements PassiveSkill {
    @Override
    public String getName() {
        return "人多势众";
    }

    /**
     * 再次行动概率。
     */
    public double getPct() {
        return 0.3;
    }

    @Override
    public void init(Controller controller, Entity self) {
        self.getEventController().add(new Handler(self));
    }

    class Handler extends SealablePassiveHandler implements EventHandler<FinishActionEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(FinishActionEvent event) {
            if (!self.isDead() && RandUtil.success(getPct())) {
                self.setPosition(1.0);
            }
        }
    }
}
