package com.sine.yys.skill.passive;

import com.sine.yys.buff.buff.AddCriticalDamage;
import com.sine.yys.buff.buff.DispellableBuff;
import com.sine.yys.event.BeDamageEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 椒图-润物无声。
 * <p>
 * 附加的buff可以在回合数上叠加。
 * 暂定给所有实体加buff。
 * 暂定不对椒图传递伤害生效。
 * XXX 润物无声被混乱下的效果。
 */
public class RunWuWuSheng extends BasePassiveSkill implements PassiveSkill, EventHandler<BeDamageEvent> {
    @Override
    public String getName() {
        return "润物无声";
    }

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
        if (event.getType().isJiaoTu())
            return;
        for (Entity entity : getOwn().getAllAlive()) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(getSelf(), this));
                CriticalDamageBuff buff = entity.getBuffController().get(CriticalDamageBuff.class);
                if (buff == null) {
                    buff = new CriticalDamageBuff(getLast(), getName(), getCriticalDamage(), getSelf());
                    entity.getBuffController().add(buff);
                } else {
                    buff.addLast(getLast());
                }
                log.info(Msg.addBuff(getSelf(), entity, buff));
            }
        }
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    class CriticalDamageBuff extends AddCriticalDamage implements DispellableBuff {
        /**
         * @param last           持续回合数。必须为正。
         * @param prefix         名称前缀。
         * @param criticalDamage 暴伤。
         * @param src            来源式神。
         */
        CriticalDamageBuff(int last, String prefix, double criticalDamage, Entity src) {
            super(last, prefix, criticalDamage, src);
        }
    }
}
