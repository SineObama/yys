package com.sine.yys.skill.passive;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.effect.BaseDebuffEffect;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.event.JuanLiuDamageEvent;
import com.sine.yys.inter.*;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 雪童子-霜天之织。
 * <p>
 * 规则：
 * 1. 攻击目标触发，不需要造成伤害；
 * 2. 涓流传递效果时需要对最终目标造成伤害（没有受伤的式神不会触发）；
 * 3. 薙魂后涓流不会触发；
 * 4. 一次伤害中只能冰冻或者破冰，不能都发生（雪幽魂的情况，还未实现）；
 * 5. 打草人也可以传递（未实现）；
 */
public class ShuangTianZhiZhi extends BasePassiveSkill implements EventHandler<AttackEvent>, PctEffect {
    private final DebuffEffect effect = new BaseDebuffEffect(getPct(), getName()) {
        @Override
        public IBuff getDebuff(Entity self) {
            return new BingDong(getLast(), self);
        }
    };

    @Override
    public double getPct() {
        return 0.45;
    }

    public int getLast() {
        return RandUtil.success(getLast2Pct()) ? 2 : 1;
    }

    public double getBreakCoefficient() {
        return 3.0;
    }

    /**
     * @return 效果持续2回合的概率。
     */
    public double getLast2Pct() {
        return 0.5;
    }

    @Override
    public String getName() {
        return "霜天之织";
    }

    @Override
    public void handle(AttackEvent event) {
        event.multiplyCoefficient(apply(event.getTarget()));
    }

    private double apply(Entity target) {
        if (target.getBuffController().contain(BingDong.class)) {
            log.info(Msg.vector(getSelf(), "对", target, "破冰"));
            target.getBuffController().remove(BingDong.class);
            return getBreakCoefficient();
        }
        getController().applyDebuff(getSelf(), target, effect);
        return 1.0;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
        self.getEventController().add(new JuanLiuDamageHandler());
    }

    class JuanLiuDamageHandler implements EventHandler<JuanLiuDamageEvent> {
        @Override
        public void handle(JuanLiuDamageEvent event) {
            event.multiplyCoefficient(apply(event.getEntity()));
        }
    }
}
