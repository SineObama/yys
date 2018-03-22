package com.sine.yys.simulation.component;

import com.sine.yys.base.SimpleObject;
import com.sine.yys.buff.BattleFlag;
import com.sine.yys.buff.BattleFlagSource;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 裁判旗子。
 * <p>
 * 监听了{@linkplain EnterEvent 进场事件}以添加{@linkplain BattleFlag 战场旗帜效果}。
 */
public final class BattleKoinobori extends SimpleObject {
    private final double damageRatioAddition = 0.15;
    private final double cureRatioReduction = -0.10;
    private final BattleFlagSourceImpl battleFlagSource = new BattleFlagSourceImpl();
    private final EventHandler<EnterEvent> enterHandler = event -> event.getEntity().getBuffController().add(new BattleFlag(battleFlagSource));
    private final BaseCamp camp0;
    private final BaseCamp camp1;
    private int level = 0;

    public BattleKoinobori(double speed, BaseCamp camp0, BaseCamp camp1) {
        super("裁判旗子", speed);
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    @Override
    public void action() {
        level += 1;
        battleFlagSource.setDamage(level * damageRatioAddition);
        battleFlagSource.setCure(level * cureRatioReduction);
        log.info(Msg.info(this, "战场旗帜等级", level));
        log.info(Msg.info(this, "伤害加成", battleFlagSource.getDamageUp()));
        log.info(Msg.info(this, "治疗衰减", battleFlagSource.getCure()));
    }

    @Override
    protected void doInit() {
        camp0.getEventController().add(EnterEvent.class, enterHandler);
        camp1.getEventController().add(EnterEvent.class, enterHandler);
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
