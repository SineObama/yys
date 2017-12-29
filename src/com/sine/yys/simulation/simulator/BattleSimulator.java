package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.*;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.entity.BattleKoinobori;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.List;

/**
 * 一场战斗。
 * 包含2个阵营。
 */
public class BattleSimulator {
    private final Camp camp0, camp1;
    private boolean auto = true;
    private OperationHandler handler = null;

    private BattleKoinobori battleKoinobori = null;  // 鲤鱼旗
    private boolean started = false;
    private SpeedBar speedBar = null;

    public BattleSimulator(final Camp camp0, final Camp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
        battleKoinobori = new BattleKoinobori();
        battleKoinobori.setSpeed(100.0);
    }

    public void step() {
        if (!started) {
            final List<Entity> all = camp0.getAllEntity();
            all.addAll(camp1.getAllEntity());
            speedBar.addAll(all);
            // TODO 战斗开始事件
        }
        Entity cur = speedBar.step();
        List<Skill> skills = cur.getSkills();
        // 百目鬼操作空间=。=

        // TODO 行动前事件

        final Camp own, enemy;
        if (camp0.contain(cur)) {
            own = camp0;
            enemy = camp1;
        } else {
            own = camp1;
            enemy = camp0;
        }
        final TargetResolver resolver = new CampBasedTargetResolver(own, enemy);
        final Operation operation = handler.handle(cur, own, resolver);

        operation.getSkill().doApply(cur, own, enemy, operation.getTarget());

        // TODO 行动后事件
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
