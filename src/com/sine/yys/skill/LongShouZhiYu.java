package com.sine.yys.skill;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.DefenseIBuff;
import com.sine.yys.buff.EffectDefIBuff;
import com.sine.yys.event.*;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 辉夜姬-龙首之玉。
 * <p>
 * 附带开局释放效果（游戏中写在被动技能中）。
 * <p>
 * 规则：
 * 1. 开局战备动作可以触发招财猫；
 * 2. 开局战备动作可以触发溢彩。
 */
public class LongShouZhiYu extends BaseNoTargetSkill implements PctEffect {
    private final BeforeActionHandler beforeActionHandler = new BeforeActionHandler();

    @Override
    public void doApply(Entity target) {
        deploy();
    }

    @Override
    public int getFire() {
        return 2;
    }

    private void deploy() {
        Entity self = getSelf();
        Buff buff = new Buff(getLast(), () -> self.getEventController().trigger(new LongShouZhiYuOff()), self);  // XXXX 在回合后buff减1回合，为了把本回合算进去，加1
        log.info(Msg.info(self, "施放", buff.getName()));
        self.getBuffController().add(buff);
        for (ShikigamiEntity shikigami : getOwn().getAllShikigami()) {  // DESIGN 给式神不包括召唤物加buff
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
        getOwn().getEventController().add(BattleStartEvent.class, event -> {
            self.getEventController().trigger(new ZhaoCaiMaoEvent());
            deploy();
            // 还会触发匣中少女的盾
        });
        self.getEventController().add(LongShouZhiYuOff.class, event -> {
            log.info(Msg.info(getOwn(), "龙首之玉幻境结束了"));
            for (Entity shikigami : getOwn().getAllShikigami()) {
                shikigami.getBuffController().remove(LSZYDefenseBuff.class);
                shikigami.getBuffController().remove(LSZYEffectDefBuff.class);
            }
            getOwn().getEventController().remove(beforeActionHandler);
        });
    }

    /**
     * 行动前回复鬼火概率。
     */
    @Override
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

    // XXX 龙首之玉防御抵抗：考虑到日女似乎不会把这种常驻buff当做增益，而且这种buff也没什么地方用，就不定义为增益
    class LSZYDefenseBuff extends DefenseIBuff {
        LSZYDefenseBuff(double defPct, Entity src) {
            super(Integer.MAX_VALUE, LongShouZhiYu.this.getName() + "-增加", defPct, src);
        }
    }

    class LSZYEffectDefBuff extends EffectDefIBuff {
        LSZYEffectDefBuff(double effectDef, Entity src) {
            super(Integer.MAX_VALUE, LongShouZhiYu.this.getName() + "-增加", effectDef, src);
        }
    }

    class BeforeActionHandler implements EventHandler<BeforeRoundEvent> {
        /**
         * 输出正则测试，消失后没有触发：龙首之玉幻境 效果消失([\s\S](?!施放 龙首之玉))*?触发 龙首之玉
         * 输出正则测试，部署后有触发：施放 龙首之玉([\s\S](?!龙首之玉幻境 效果消失))*?触发 龙首之玉
         */
        @Override
        public void handle(BeforeRoundEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), LongShouZhiYu.this));
                event.getEntity().getFireRepo().addFire(1);
            }
        }
    }

    /**
     * 辉夜姬身上隐藏的龙首之玉buff，代表龙首之玉幻境。
     */
    public class Buff extends BaseIBuff {
        private final Callback callback;

        Buff(int last, Callback callback, Entity src) {
            super(last, "龙首之玉", src);
            this.callback = callback;
        }

        @Override
        public void onRemove(Entity self) {
            callback.call();
        }
    }

}
