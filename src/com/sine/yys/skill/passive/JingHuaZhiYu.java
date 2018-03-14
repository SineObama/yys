package com.sine.yys.skill.passive;

import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.event.BeforeActionEvent;
import com.sine.yys.inter.BuffController;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

/**
 * 雨女-净化之雨。
 */
public class JingHuaZhiYu extends BasePassiveSkill implements PassiveSkill, EventHandler<BeforeActionEvent> {
    @Override
    public String getName() {
        return "净化之雨";
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this, 400);
    }

    @Override
    public void handle(BeforeActionEvent event) {
        final Entity self = getSelf();
        final BuffController buffController = self.getBuffController();
        for (DispellableDebuff debuff : buffController.getBuffs(DispellableDebuff.class)) {
            buffController.remove(debuff.getClass());
        }
    }
}