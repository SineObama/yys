package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.ActiveSkill;
import com.sine.yys.simulation.component.BaseEntity;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.InitContext;
import com.sine.yys.simulation.component.model.Shikigami;
import com.sine.yys.simulation.component.model.buff.buff.LSZYDefenseBuff;
import com.sine.yys.simulation.component.model.buff.buff.LSZYEffectDefBuff;
import com.sine.yys.simulation.component.model.buff.buff.LongShouZhiYuBuff;
import com.sine.yys.simulation.component.model.event.BattleStartEvent;
import com.sine.yys.simulation.component.model.event.BeforeActionEvent;
import com.sine.yys.simulation.component.model.event.LongShouZhiYuOff;
import com.sine.yys.simulation.component.model.event.LongShouZhiYuOn;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 辉夜姬-龙首之玉。
 * 附带开局释放效果（游戏中写在被动技能中）。
 */
public class LongShouZhiYu extends BaseNoTargetSkill implements ActiveSkill, EventHandler<BeforeActionEvent> {
    @Override
    public int getFire() {
        return 2;
    }

    @Override
    public void apply(Entity target) {
        deploy(getLast() + 1);
    }

    /**
     * 释放幻境。由于开局释放不算回合数，而技能释放时需要给last额外加1，所以独立出这个逻辑。
     */
    private void deploy(int last) {
        BaseEntity self = getSelf();
        LongShouZhiYuBuff buff = new LongShouZhiYuBuff(last, () -> self.getEventController().trigger(new LongShouZhiYuOff()));  // XXXX 在回合后buff减1回合，为了把本回合算进去，加1
        log.info(Msg.info(self, "施放 " + buff.getName()));
        self.getBuffController().addBuff(buff);
        for (Shikigami shikigami : self.getCamp().getAllShikigami()) {  // DESIGN 给式神不包括召唤物加buff
            shikigami.getBuffController().addAttach(new LSZYDefenseBuff(getDefPct()));
            shikigami.getBuffController().addAttach(new LSZYEffectDefBuff(getEffectDef()));
        }
        self.getCamp().getEventController().add(this);
        self.getEventController().trigger(new LongShouZhiYuOn());
    }

    @Override
    public String getName() {
        return "龙首之玉";
    }

    /**
     * 输出正则测试，消失后没有触发：龙首之玉幻境 效果消失([\s\S](?!施放 龙首之玉))*?触发 龙首之玉
     * 输出正则测试，部署后有触发：施放 龙首之玉([\s\S](?!龙首之玉幻境 效果消失))*?触发 龙首之玉
     */
    @Override
    public void handle(BeforeActionEvent event) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            event.getTarget().getFireRepo().addFire(1);
        }
    }

    @Override
    public void doInit(InitContext context) {
        getSelf().getCamp().getEventController().add(new EventHandler<BattleStartEvent>() {
            @Override
            public void handle(BattleStartEvent event) {
                // XXX 实际上会触发招财猫（甚至是一个回合，因为在行动条上显示了辉夜姬），然而感觉很bug
                // 然而又不能算一个回合（没有触发彼岸花被动？也不会触发御馔津吧？），因为鬼火进度条没变……
                deploy(getLast());
            }
        });
        LongShouZhiYu instance = this;
        getSelf().getEventController().add(new EventHandler<LongShouZhiYuOff>() {
            @Override
            public void handle(LongShouZhiYuOff event) {
                getSelf().getCamp().getEventController().remove(instance);
                for (Shikigami shikigami : getSelf().getCamp().getAllShikigami()) {
                    shikigami.getBuffController().removeAttach(LSZYDefenseBuff.class);
                    shikigami.getBuffController().removeAttach(LSZYEffectDefBuff.class);
                }
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
}
