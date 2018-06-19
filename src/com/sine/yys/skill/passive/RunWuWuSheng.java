package com.sine.yys.skill.passive;

import com.sine.yys.buff.buff.AddCriticalDamage;
import com.sine.yys.event.BeDamageEvent;
import com.sine.yys.inter.*;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.List;

/**
 * 椒图-润物无声。
 * <p>
 * 规则：
 * 1. buff可以在回合数上叠加；
 * 2. 涓流传递伤害无效；
 * 3. 暂定给所有实体加buff；
 * 4. 暂定不对椒图传递伤害生效；
 * 5. XXX 润物无声被混乱下的效果未确定。
 */
public class RunWuWuSheng extends BasePassiveSkill implements EventHandler<BeDamageEvent>, PctEffect {
    @Override
    public String getName() {
        return "润物无声";
    }

    @Override
    public double getPct() {
        return 0.5;
    }

    public double getCriticalDamage() {
        return 0.2;
    }

    /**
     * @return 效果持续回合数。
     */
    public int getLast() {
        return 2;
    }

    @Override
    public void handle(BeDamageEvent event) {
        if (event.getType().isJuanLiu())
            return;
        for (Entity entity : getOwn().getAllAlive()) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), this));
                AddCriticalDamage buff = new Buff();
                entity.getBuffController().add(buff);
                log.info(Msg.addBuff(getSelf(), entity, buff));
            }
        }
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    public class Buff extends AddCriticalDamage {
        Buff() {
            super(RunWuWuSheng.this.getLast(), RunWuWuSheng.this.getName(), RunWuWuSheng.this.getCriticalDamage(), RunWuWuSheng.this.getSelf());
        }

        @Override
        public IBuff replace(List<IBuff> buffs) {
            final IBuff iBuff = buffs.get(0);
            iBuff.addLast(RunWuWuSheng.this.getLast());
            return null;
        }
    }
}
