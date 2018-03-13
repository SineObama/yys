package com.sine.yys.skill;

import com.sine.yys.buff.buff.LSZYDefenseBuff;
import com.sine.yys.buff.buff.LSZYEffectDefBuff;
import com.sine.yys.buff.buff.LongShouZhiYuBuff;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.event.BeforeActionEvent;
import com.sine.yys.event.LongShouZhiYuOff;
import com.sine.yys.event.LongShouZhiYuOn;
import com.sine.yys.inter.*;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 辉夜姬-龙首之玉。
 * 附带开局释放效果（游戏中写在被动技能中）。
 */
public class LongShouZhiYu extends BaseNoTargetSkill implements ActiveSkill {
    private final BeforeActionHandler beforeActionHandler = new BeforeActionHandler();

    @Override
    public int getFire() {
        return 2;
    }

    @Override
    public void doApply(Entity target) {
        deploy();
    }

    /**
     * 释放幻境。由于开局释放不算回合数，而技能释放时需要给last额外加1，所以独立出这个逻辑。
     */
    private void deploy() {
        Entity self = getSelf();
        LongShouZhiYuBuff buff = new LongShouZhiYuBuff(getLast(), () -> self.getEventController().trigger(new LongShouZhiYuOff()), self);  // XXXX 在回合后buff减1回合，为了把本回合算进去，加1
        log.info(Msg.info(self, "施放", buff.getName()));
        self.getBuffController().add(buff);
        for (ShikigamiEntity shikigami : getController().getCamp(self).getAllShikigami()) {  // DESIGN 给式神不包括召唤物加buff
            shikigami.getBuffController().add(new LSZYDefenseBuff(getDefPct(), self));
            shikigami.getBuffController().add(new LSZYEffectDefBuff(getEffectDef(), self));
        }
        getOwn().getEventController().add(beforeActionHandler);
        self.getEventController().trigger(new LongShouZhiYuOn());
    }

    @Override
    public String getName() {
        return "龙首之玉";
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        getOwn().getEventController().add(new EventHandler<BattleStartEvent>() {
            @Override
            public void handle(BattleStartEvent event) {
                // XXX 实际上会触发招财猫（甚至是一个回合，因为在行动条上显示了辉夜姬），然而感觉很bug
                // 然而又不能算一个回合（没有触发彼岸花被动，也不会触发御馔津），因为鬼火进度条没变……
                // 好像还会触发匣中少女的盾，无语。还是可以考虑定义一个新概念。
                deploy();
            }
        });
        self.getEventController().add(new EventHandler<LongShouZhiYuOff>() {
            @Override
            public void handle(LongShouZhiYuOff event) {
                log.info(Msg.info(getOwn(), "龙首之玉幻境结束了"));
                for (Entity shikigami : getOwn().getAllShikigami()) {
                    shikigami.getBuffController().remove(LSZYDefenseBuff.class);
                    shikigami.getBuffController().remove(LSZYEffectDefBuff.class);
                }
                getOwn().getEventController().remove(beforeActionHandler);
            }
        });
    }

    /**
     * 行动前回复鬼火概率。
     */
    public double getPct() {
        return 2.0 / 3.0;
    }

    /**
     * 幻境防御加成百分比。
     */
    public double getDefPct() {
        return 0.25;
    }

    /**
     * 幻境效果抵抗加成。
     */
    public double getEffectDef() {
        return 0.20;
    }

    /**
     * 幻境持续回合。
     */
    public int getLast() {
        return 2;
    }

    class BeforeActionHandler implements EventHandler<BeforeActionEvent> {
        /**
         * 输出正则测试，消失后没有触发：龙首之玉幻境 效果消失([\s\S](?!施放 龙首之玉))*?触发 龙首之玉
         * 输出正则测试，部署后有触发：施放 龙首之玉([\s\S](?!龙首之玉幻境 效果消失))*?触发 龙首之玉
         */
        @Override
        public void handle(BeforeActionEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), LongShouZhiYu.this));
                event.getEntity().getFireRepo().addFire(1);
            }
        }
    }
}
