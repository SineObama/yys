package com.sine.yys.simulation.component;

import com.sine.yys.buff.buff.BattleFlag;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 战场鲤鱼旗。
 * 还未实现功能。
 */
public class BattleKoinobori extends SimpleObject implements EventHandler<BattleStartEvent> {
    private final double damageRatioAddition = 0.15;
    private final double cureRatioReduction = -0.15;
    private int level = 0;
    private BattleFlag battleFlag = new BattleFlag();

    public BattleKoinobori(double speed) {
        super("战场鲤鱼旗", speed);  // TODO 行动技能：给全体加增伤buff
    }

    public double getDamageRatioAddition() {
        return damageRatioAddition;
    }

    public double getCureRatioReduction() {
        return cureRatioReduction;
    }

    @Override
    void action() {
        level += 1;
        battleFlag.setDamage(level * damageRatioAddition);
        battleFlag.setCure(level * cureRatioReduction);
        log.info(Msg.info(this, "战场旗帜等级 " + level));
        log.info(Msg.info(this, "伤害加成 " + battleFlag.getDamageUp()));
        log.info(Msg.info(this, "治疗衰减 " + battleFlag.getCure()));
    }

    public BattleFlag getBattleFlag() {
        return battleFlag;
    }

    @Override
    protected void doInit() {
        // XXXX 临时监听其中一个阵营的开始事件。
        getController().getCamp0().getEventController().add(this);
    }

    @Override
    public void handle(BattleStartEvent event) {
        for (Entity entity : getController().getCamp0().getAllAlive()) {
            entity.getBuffController().add(battleFlag);
        }
        for (Entity entity : getController().getCamp1().getAllAlive()) {
            entity.getBuffController().add(battleFlag);
        }
    }
}
