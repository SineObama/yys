package com.sine.yys.mitama;

import com.sine.yys.buff.debuff.control.ChenMo;
import com.sine.yys.event.*;
import com.sine.yys.inter.ControlBuff;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 薙魂。
 * 在被控制（除了沉默）时不能触发。
 * 可以分担针女伤害。
 * 不能与金鱼、小松丸躲避、其他薙魂同时生效。
 * 多个薙魂时触发概率比较诡异，暂且不管。
 */
public class TiHun extends BaseMitama implements EventHandler<BeMonoAttackEvent> {
    private final DamageShareHandler damageShareHandler = new DamageShareHandler();
    private final AfterMovementHandler afterMovementHandler = new AfterMovementHandler();
    private Entity target = null;

    public double getPct() {
        return 0.5;
    }

    /**
     * @return 减伤效果。
     */
    public double getDamageReducePct() {
        return 0.2;
    }

    /**
     * @return 减伤后分担的伤害。
     */
    public double getSharePct() {
        return 0.5;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        getOwn().getEventController().add(afterMovementHandler);
    }

    @Override
    protected EventHandler<EnterEvent> getEnterHandler() {
        return event -> getOwn().getEventController().add(0, this);
    }

    @Override
    protected EventHandler<DieEvent> getDieHandler() {
        return event -> getOwn().getEventController().remove(this);
    }

    @Override
    public void handle(BeMonoAttackEvent event) {
        if (event.isTreated())
            return;
        final ControlBuff controlBuff = getSelf().getBuffController().getFirstControlBuff();
        if (controlBuff != null && !(controlBuff instanceof ChenMo))
            return;
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            event.setTreated();
            target = event.getEntity();
            target.getEventController().add(damageShareHandler);
        }
    }

    @Override
    public String getName() {
        return "薙魂";
    }

    class AfterMovementHandler implements EventHandler<AfterMovementEvent> {
        @Override
        public void handle(AfterMovementEvent event) {
            if (target != null) {
                target.getEventController().remove(damageShareHandler);
                target = null;
            }
        }
    }

    class DamageShareHandler implements EventHandler<DamageShareEvent> {
        @Override
        public void handle(DamageShareEvent event) {
            final double damage = event.getTotal() * (1 - getDamageReducePct());
            // XXXXX 不清楚单人断连的情况。当前会触发薙魂，分担的伤害不会再被椒图分担
            // XXXX 薙魂的减伤目前只对破盾后的伤害生效
            getController().directDamage(getSelf(), (int) (damage * getSharePct()));
            event.setLeft(damage * (1 - getSharePct()));
        }
    }
}
