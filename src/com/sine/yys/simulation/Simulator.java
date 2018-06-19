package com.sine.yys.simulation;

import com.sine.yys.entity.EntityImpl;
import com.sine.yys.entity.SimpleObject;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.impl.ControllerImpl;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
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

    /**
     * 模拟行动条前进，得到下一个行动式神。
     */
    private SimpleObject actionBarMove() {
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
    public void init() {
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

    /**
     * 让一个式神行动一次。
     * <p>
     * 一般是行动条前进一次，也可能出现获得新回合的情况（会返回）。另外包括行动后的反击等自动动作。
     *
     * @return 战斗是否还能继续。
     */
    public boolean step() {
        if (ended)
            return false;
        init();

        try {
            // 获取下一行动式神
            Entity next = controller.setNext(null);
            final SimpleObject self;
            final boolean newRound;
            if (next == null) {
                self = actionBarMove();
                newRound = false;
            } else {
                self = (SimpleObject) next;
                newRound = true;
            }

            round += 1;
            log.info(Msg.info(self, "行动，序号", round));
            self.action(newRound);
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

        return !checkWin();
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
