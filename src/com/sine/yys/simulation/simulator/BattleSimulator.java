package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.entity.BattleKoinobori;
import com.sine.yys.simulation.component.model.event.BattleStartEvent;
import com.sine.yys.simulation.util.Msg;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 自动战斗模拟器。
 * 期望用于红白竞猜模拟。
 */
public class BattleSimulator implements Simulator {
    private final Logger log = Logger.getLogger(this.getClass().toString());

    // 引用
    private final Camp camp0, camp1;
    private final BattleKoinobori battleKoinobori = new BattleKoinobori(128.0);

    // 状态
    private boolean started = false;
    private int round = 0;

    public BattleSimulator(final Camp camp0, final Camp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    private Entity next() {
        Entity rtn = null;
        double min = 1;  // 不可能达到的较大值
        List<Entity> all = new ArrayList<>(15);
        all.addAll(camp0.getAllAlive());
        all.addAll(camp1.getAllAlive());
        all.add(battleKoinobori);
        for (Entity entity : all) {
            double remain = (1 - entity.getPosition()) / entity.getSpeed();
            if (min > remain) {
                min = remain;
                rtn = entity;
            }
        }
        for (Entity entity : all) {
            entity.setPosition(entity.getPosition() + min * entity.getSpeed());
        }
        return rtn;
    }

    @Override
    public Camp step() {
        // 初始化
        if (!started) {
            started = true;

            // 类似树的结构进行初始化，阵营-式神-技能
            // 注入各对象；技能监听事件
            InitContext context = new InitContext();
            context.setOwn(camp0);
            context.setEnemy(camp1);
            camp0.init(context);
            context.setOwn(camp1);
            context.setEnemy(camp0);
            camp1.init(context);

            BattleStartEvent startEvent = new BattleStartEvent();
            camp0.getEventController().trigger(startEvent);
            camp1.getEventController().trigger(startEvent);
        }

        // 获取下一行动式神
        Entity self = next();

        // TODO 战场鲤鱼旗行动
        if (self == battleKoinobori) {
            self.setPosition(0);
            return null;
        }

        round += 1;
        log.info(Msg.info(self, "行动，序号 " + round));

        self.action();

        // XXX 简单判断胜负：无式神存活
        if (camp0.getAllShikigami().size() == 0)
            return camp1;
        if (camp1.getAllShikigami().size() == 0)
            return camp0;
        return null;
    }

    @Override
    public int getRound() {
        return round;
    }
}
