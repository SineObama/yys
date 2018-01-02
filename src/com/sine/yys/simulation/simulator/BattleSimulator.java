package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.SpeedBar;
import com.sine.yys.simulation.component.SpeedBarImpl;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.battle.ActionContextImpl;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.BattleKoinobori;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.CommonAttack;
import com.sine.yys.simulation.model.skill.SimpleGroupAttack;
import com.sine.yys.simulation.model.skill.Skill;
import com.sine.yys.simulation.rule.CalcDam;
import com.sine.yys.simulation.util.Msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 一场战斗。
 * 包含2个阵营。
 */
public class BattleSimulator {
    private final Logger log = Logger.getLogger(this.getClass().toString());
    private final Camp camp0, camp1;
    private boolean auto = true;
    private OperationHandler handler = null;

    private BattleKoinobori battleKoinobori = null;  // 鲤鱼旗
    private boolean started = false;
    private SpeedBar speedBar = null;

    private int round = 0;

    public BattleSimulator(final Camp camp0, final Camp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
        this.battleKoinobori = new BattleKoinobori(100.0);
        this.speedBar = new SpeedBarImpl();
    }

    public Camp step() {
        if (!started) {
            started = true;
            // 为技能添加事件回调处理函数？

            // 加入到行动条中。
            final List<Entity> all = camp0.getAllAlive();
            all.addAll(camp1.getAllAlive());
            speedBar.addAll(all);

            // TODO 战斗开始事件
        }

        // 获取当前行动式神
        Entity cur = speedBar.step();

        // 确定敌我阵营
        final Camp own, enemy;
        if (camp0.contain(cur)) {
            own = camp0;
            enemy = camp1;
        } else {
            own = camp1;
            enemy = camp0;
        }
        // 鬼火行动条推进一格。
        own.step();

        round += 1;
        log.info(Msg.turn(own, cur) + " 序号 " + round);

        // TODO 行动前事件

        // 获取每个主动技能的可选目标
        Map<ActiveSkill, List<? extends Target>> map = new HashMap<>();
        for (Skill skill : cur.getSkills()) {
            if (skill instanceof ActiveSkill) {
                map.put((ActiveSkill) skill, ((ActiveSkill) skill).getTargetResolver().resolve(cur, own, enemy));
            }
        }

        // 获取操作，手动或自动AI选择。
        final Operation operation;
        if (auto)
            operation = cur.getAI().handle(cur, own, map);
        else
            operation = handler.handle(cur, own, map);
        log.info(Msg.action(own, cur, operation.getSkill()));

        // 实现操作
        ActiveSkill skill = operation.getSkill();
        if (operation.getTarget() instanceof Entity)
            skill.apply(new ActionContextImpl(cur, (Entity) operation.getTarget(), own, enemy));
        else
            skill.apply(new ActionContextImpl(cur, null, own, enemy));

        // TODO 行动后事件

        if (own.getAllShikigami().size() == 0)
            return enemy;
        if (enemy.getAllShikigami().size() == 0)
            return own;
        return null;
    }

    public Camp getCamp0() {
        return camp0;
    }

    public Camp getCamp1() {
        return camp1;
    }

    public double getDamageRatio() {
        return battleKoinobori.getDamageRatio();
    }

    public void setDamageRatio(double damageRatio) {
        this.battleKoinobori.setDamageRatio(damageRatio);
    }

    public double getCureRatio() {
        return battleKoinobori.getCureRatio();
    }

    public void setCureRatio(double cureRatio) {
        this.battleKoinobori.setCureRatio(cureRatio);
    }

    public double getDamageRatioAddition() {
        return battleKoinobori.getDamageRatioAddition();
    }

    public void setDamageRatioAddition(double damageRatioAddition) {
        this.battleKoinobori.setDamageRatioAddition(damageRatioAddition);
    }

    public double getCureRatioReduction() {
        return battleKoinobori.getCureRatioReduction();
    }

    public void setCureRatioReduction(double cureRatioReduction) {
        this.battleKoinobori.setCureRatioReduction(cureRatioReduction);
    }

    public double getKoinoboriSpeed() {
        return battleKoinobori.getSpeed();
    }

    public void setKoinoboriSpeed(double speed) {
        battleKoinobori.setSpeed(speed);
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public OperationHandler getHandler() {
        return handler;
    }

    public void setHandler(OperationHandler handler) {
        this.handler = handler;
    }
}
