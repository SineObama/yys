package com.sine.yys.simulation.component;

import com.sine.yys.base.SimpleObject;
import com.sine.yys.event.*;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.inter.base.Skill;
import com.sine.yys.util.Msg;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 战场模拟器。安排战斗主干流程。
 */
public class Simulator {
    private final Logger log = Logger.getLogger(getClass().getName());

    // 引用
    private final BaseCamp camp0, camp1;
    private final List<SimpleObject> extras;  // 额外的对象，包括不属于阵营的裁判旗子。秘闻竞赛副本的鬼头？
    private final ControllerImpl controller;
    private Camp win = null;
    // 状态
    private boolean started = false;
    private boolean ended = false;
    private int round = 0;

    public Simulator(final BaseCamp camp0, final BaseCamp camp1, List<SimpleObject> extras) {
        this.camp0 = camp0;
        this.camp1 = camp1;
        this.extras = extras;
        controller = new ControllerImpl(camp0, camp1);
    }

    private SimpleObject next() {
        double min = 1.0;  // 不可能达到的较大值
        List<SimpleObject> all = new ArrayList<>(15);
        all.addAll(camp0.getAllAlive());
        all.addAll(camp1.getAllAlive());
        all.addAll(extras);
        SimpleObject rtn = all.get(0);
        for (SimpleObject entity : all) {
            double remain = (1.0 - entity.getPosition()) / entity.getSpeed();
            if (min > remain) {
                min = remain;
                rtn = entity;
            } else if (min == 0.0 && remain == 0.0 && entity.getSpeed() > rtn.getSpeed()) {
                rtn = entity;
            }
        }
        if (min != 0.0)
            for (SimpleObject entity : all)
                entity.addPosition(min * entity.getSpeed());
        rtn.setPosition(1.0);
        return rtn;
    }

    /**
     * 初始化。
     */
    private void init() {
        started = true;
        camp0.init(camp1, controller);
        camp1.init(camp0, controller);
        for (SimpleObject extra : extras)
            extra.init(controller);
        for (EntityImpl entity : camp0.getAllAlive())
            entity.eventController.trigger(new EnterEvent(entity));
        camp0.getEventController().trigger(new BattleStartEvent());
        for (EntityImpl entity : camp1.getAllAlive())
            entity.eventController.trigger(new EnterEvent(entity));
        camp1.getEventController().trigger(new BattleStartEvent());
    }

    public void step() {
        if (ended)
            return;
        if (!started)
            init();

        try {

            // 获取下一行动式神
            final SimpleObject self0 = next();

            // 裁判旗子（等独立实体）行动。直接调用 action，跳过鬼火仓库、技能调用等……
            if (!(self0 instanceof EntityImpl)) {
                self0.setPosition(0);
                self0.action();
                return;
            }
            final EntityImpl self = (EntityImpl) self0;

            /*
             * 整个行动，包括鬼火处理、技能处理、事件触发、行动后的反击等。
             * 多次行动不会返回。
             */

            // 预备推进鬼火行动条
            self.fireRepo.ready();

            // 用于多次行动
            do {
                round += 1;
                log.info(Msg.info(self, "行动，序号", round));

                // 重置行动条。使死于赤团华或buff的情况下行动条归0
                self.setPosition(0);

                for (Skill skill : self.shikigami.getSkills())
                    skill.beforeAction();

                // 回合前事件
                // 为了行动前彼岸花的控制效果生效，事件要在buff调用之前。
                self.eventController.trigger(new ZhaoCaiMaoEvent());
                self.eventController.trigger(new BeforeRoundEvent(self));

                controller.afterMovement();

                // 包括执行持续伤害、治疗
                self.buffController.beforeAction(controller);

                if (!self.isDead())
                    self.action();

                controller.afterMovement();

                // buff回合数-1
                self.buffController.afterAction(controller);

                // 回合后事件
                self.eventController.trigger(new AfterRoundEvent(self));

                for (Skill skill : self.shikigami.getSkills())
                    skill.afterAction();

                // 完成推进鬼火行动条
                self.fireRepo.finish();

                log.info(Msg.info(self, "行动结束，序号", round));
                if (checkWin())
                    break;

                // 回合后的行动，如反击等。通过回调实现。
                Callback action = controller.getFirstAction();
                while (action != null) {
                    action.call();
                    controller.afterMovement();
                    if (checkWin())
                        break;
                    action = controller.getFirstAction();
                }
                if (checkWin())
                    break;
            } while (self.getPosition() == 1.0 && !self.isDead());

        } catch (Exception e) {
            log.severe(camp0.toJSON());
            log.severe(camp1.toJSON());
            throw e;
        }
    }

    private boolean checkWin() {
        if (ended)
            return true;
        // XXX 简单判断胜负：无式神存活
        if (camp0.getAllShikigami().size() == 0) {
            win = camp1;
            return ended = true;
        }
        if (camp1.getAllShikigami().size() == 0) {
            win = camp0;
            return ended = true;
        }
        return false;
    }

    public Camp getWin() {
        return win;
    }

    public int getRound() {
        return round;
    }
}
