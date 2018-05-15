package com.sine.yys.skill.passive;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.effect.BaseDebuffEffect;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.event.STZZEnterEvent;
import com.sine.yys.event.STZZExitEvent;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.mitama.XueYouHun;
import com.sine.yys.transeffect.STZZ;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 雪童子-霜天之织。
 * <p>
 * 当前实现非常的侵入：
 * 在攻击时{@linkplain AttackEvent}中向{@linkplain AttackType}添加{@linkplain TransferrableEffect 效果}。
 * 然后需要效果作为信号触发进入退出事件，对可能的雪幽魂进行操作。
 * 而且在初始化时获取雪幽魂，进行禁用的准备，不支持中途添加的御魂。
 * <p>
 * 规则：
 * 1. 攻击目标触发，不需要造成伤害；
 * 2. 涓流传递效果时需要对最终目标造成伤害（没有受伤的式神不会触发）；
 * 3. 薙魂后涓流不会触发；
 * 4. 一次伤害中只能冰冻或者破冰，不能都发生；
 * 5. 打草人（未实现）也可以传递；
 */
public class ShuangTianZhiZhi extends BasePassiveSkill implements EventHandler<AttackEvent>, PctEffect {
    private final DebuffEffect effect = new BaseDebuffEffect(getPct(), getName()) {
        @Override
        public IBuff getDebuff(Entity self) {
            return new BingDong(getLast(), self);
        }
    };
    private XueYouHun xueYouHun = null;
    private Callback forbid;
    private Callback unforbid;

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

    /**
     * 判断冻结、破冰并相应禁用雪幽魂。返回伤害加成系数。
     */
    private double apply(Entity target) {
        if (target.getBuffController().contain(BingDong.class)) {
            log.info(Msg.trigger(getSelf(), this));
            log.info(Msg.vector(getSelf(), "对", target, "破冰"));
            target.getBuffController().remove(BingDong.class);
            forbid.call();
            return getBreakCoefficient();
        }
        getController().applyDebuff(getSelf(), target, effect);
        if (target.getBuffController().contain(BingDong.class)) {
            log.info(Msg.trigger(getSelf(), this));
            log.info(Msg.vector(getSelf(), "冰冻", target));
            forbid.call();
        }
        return 1.0;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(new STZZEnterHandler());
        self.getEventController().add(new STZZExitHandler());
        if (self instanceof ShikigamiEntity) {
            for (Mitama mitama : ((ShikigamiEntity) self).getMitamas()) {
                if (mitama instanceof XueYouHun) {
                    xueYouHun = ((XueYouHun) mitama);
                    break;
                }
            }
        }
        if (xueYouHun != null) {
            forbid = () -> {
                xueYouHun.setForbidden(true);
                log.fine("临时禁用雪幽魂");
            };
            unforbid = () -> {
                xueYouHun.setForbidden(false);
                log.fine("取消禁用雪幽魂");
            };
        } else {
            forbid = () -> {
            };
            unforbid = () -> {
            };
        }
    }

    @Override
    public void handle(AttackEvent event) {
        event.getAttackType().getEffects().put(STZZ.class, new STZZ(getSelf()));
    }

    class STZZEnterHandler implements EventHandler<STZZEnterEvent> {
        @Override
        public void handle(STZZEnterEvent event) {
            event.multiplyCoefficient(apply(event.getTarget()));
        }
    }

    class STZZExitHandler implements EventHandler<STZZExitEvent> {
        @Override
        public void handle(STZZExitEvent event) {
            unforbid.call();
        }
    }
}
