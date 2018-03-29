package com.sine.yys.mitama;

import com.sine.yys.buff.debuff.control.ChenMo;
import com.sine.yys.buff.debuff.control.ControlBuff;
import com.sine.yys.event.*;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.skill.JuanLiu;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 薙魂。
 * <p>
 * 在一个动作中，友方单位被指向性的单体攻击时概率触发，分担该动作过程中该单位受到的一般伤害（不包括草人）。
 * 最简单的例子是被普攻时触发对方协战，分担普攻和协战的伤害。
 * <p>
 * 规则：
 * 1. 在被控制（除了沉默）时不能触发；
 * 2. 可以分担针女伤害；
 * 3. 不能与金鱼、小松丸躲避、其他薙魂同时生效。
 * （多个薙魂时触发概率比较诡异，暂且不管。）
 */
public class TiHun extends BaseMitama implements EventHandler<BeMonoAttackEvent>, PctEffect {
    private final DamageShareHandler damageShareHandler = new DamageShareHandler();
    private final AfterMovementHandler afterMovementHandler = new AfterMovementHandler();
    private Entity target = null;

    @Override
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
        return event -> {
            getOwn().getEventController().remove(this);
            if (target != null) {
                target.getEventController().remove(damageShareHandler);
                target = null;
            }
        };
    }

    @Override
    public void handle(BeMonoAttackEvent event) {
        if (event.isTreated() || event.getEntity() == getSelf() || event.getEntity().getBuffController().contain(JuanLiu.JuanLiuBuff.class))
            return;
        final ControlBuff controlBuff = getSelf().getBuffController().getFirstWithPrior(ControlBuff.class);
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
            // XXXX 薙魂的减伤只对破盾后的伤害生效
            final double damage = event.getTotal() * (1 - getDamageReducePct());
            event.getType().setTiHun(true);
            // 单人断涓流的也会触发薙魂
            getController().directDamage(event.getEntity(), getSelf(), (int) (damage * getSharePct()), event.getType());
            event.setLeft(damage * (1 - getSharePct()));
        }
    }
}
