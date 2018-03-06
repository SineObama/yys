package com.sine.yys.simulation.component;

import com.sine.yys.buff.buff.BattleFlag;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

/**
 * 战场鲤鱼旗。
 * 还未实现功能。
 */
public class BattleKoinobori extends EntityImpl implements Entity, EventHandler<BattleStartEvent> {
    private int level = 0;
    private final double damageRatioAddition = 0.15;
    private final double cureRatioReduction = 0.15;
    private BattleFlag battleFlag = new BattleFlag(this);

    public BattleKoinobori(double speed) {
        super(new PropertyImpl(0, 0, 0, speed, 0, 0, 0, 0), null, null);  // TODO 行动技能：给全体加增伤buff
    }

    public double getDamageRatioAddition() {
        return damageRatioAddition;
    }

    public double getCureRatioReduction() {
        return cureRatioReduction;
    }

    public void levelUp() {
        level += 1;
        battleFlag.setDamage(level * damageRatioAddition);
        battleFlag.setCure(level * cureRatioReduction);
    }

    public BattleFlag getBattleFlag() {
        return battleFlag;
    }

    @Override
    public void handle(BattleStartEvent event) {

    }
}
