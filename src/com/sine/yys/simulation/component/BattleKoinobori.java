package com.sine.yys.simulation.component;

import com.sine.yys.buff.buff.BattleFlag;
import com.sine.yys.buff.buff.BattleFlagSource;
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
    private final double cureRatioReduction = -0.10;
    private int level = 0;
    private BattleFlagSourceImpl battleFlagSource = new BattleFlagSourceImpl();

    public BattleKoinobori(double speed) {
        super("战场鲤鱼旗", speed);
    }

    @Override
    void action() {
        level += 1;
        battleFlagSource.setDamage(level * damageRatioAddition);
        battleFlagSource.setCure(level * cureRatioReduction);
        log.info(Msg.info(this, "战场旗帜等级", level));
        log.info(Msg.info(this, "伤害加成", battleFlagSource.getDamageUp()));
        log.info(Msg.info(this, "治疗衰减", battleFlagSource.getCure()));
    }

    @Override
    protected void doInit() {
        // XXXX 临时监听其中一个阵营的开始事件。
        getController().getCamp0().getEventController().add(this);
    }

    @Override
    public void handle(BattleStartEvent event) {
        for (Entity entity : getController().getCamp0().getAllAlive()) {
            entity.getBuffController().add(new BattleFlag(battleFlagSource));
        }
        for (Entity entity : getController().getCamp1().getAllAlive()) {
            entity.getBuffController().add(new BattleFlag(battleFlagSource));
        }
    }

    class BattleFlagSourceImpl implements BattleFlagSource {
        private double damage = 0, cure = 0;

        public void setDamage(double damage) {
            this.damage = damage;
        }

        @Override
        public double getCure() {
            return cure;
        }

        public void setCure(double cure) {
            this.cure = cure;
        }

        @Override
        public double getDamageUp() {
            return damage;
        }
    }
}
