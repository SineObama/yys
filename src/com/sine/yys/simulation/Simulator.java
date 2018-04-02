package com.sine.yys.simulation;

import com.sine.yys.entity.EntityImpl;
import com.sine.yys.entity.SimpleObject;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.impl.ControllerImpl;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.base.Callback;
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
        rtn.setPosition(0.0);  // 行动条位置也用于保存再次行动的信息，提前重置
        return rtn;
    }

    /**
     * 初始化。
     */
    private void init() {
        if (started)
            return;
        started = true;
        camp0.init(camp1, controller);
        camp1.init(camp0, controller);
        for (SimpleObject extra : extras)
            extra.init(controller);
        for (EntityImpl entity : camp0.getAllAlive())
            entity.getEventController().trigger(new EnterEvent(entity));
        camp0.getEventController().trigger(new BattleStartEvent());
        for (EntityImpl entity : camp1.getAllAlive())
            entity.getEventController().trigger(new EnterEvent(entity));
        camp1.getEventController().trigger(new BattleStartEvent());
    }

    public void step() {
        if (ended)
            return;
        init();

        try {
            // 获取下一行动式神
            final SimpleObject self = next();

            round += 1;
            log.info(Msg.info(self, "行动，序号", round));
            self.action();
            log.info(Msg.info(self, "行动结束，序号", round));

            while (true) {
                controller.afterMovement();
                Callback action = controller.pollAction();
                if (checkWin() || action == null)
                    break;
                action.call();
            }

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
