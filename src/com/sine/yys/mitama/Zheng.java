package com.sine.yys.mitama;

import com.sine.yys.buff.debuff.control.ChaoFeng;
import com.sine.yys.event.AfterActionEvent;
import com.sine.yys.event.BeDamageEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.Self;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.inter.base.Named;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 狰。
 * <p>
 * 规则：
 * 1. 椒图传递的伤害可以触发（薙魂可以触发）；
 * 2. 被混乱和沉默可以正常反击，被嘲讽反击嘲讽对象，其他控制下无法反击；
 * 3. 对针女伤害独立计算，即可以单独触发第二次反击。
 * 4. 反击触发溢彩。
 */
public class Zheng extends BaseSelfMitama implements EventHandler<BeDamageEvent> {
    private Runner common = new Runner("普通狰反击");
    private Runner zhenNv = new Runner("针女狰反击");

    @Override
    public String getName() {
        return "狰";
    }

    /**
     * 对嘲讽目标概率降低60%。
     *
     * @param target 发起攻击的目标
     * @return 触发概率。
     */
    public double getPct(Entity target) {
        if (target.getBuffController().getBuffs(ChaoFeng.class).isEmpty())
            return 0.35;
        return 0.35 * 0.4;
    }

    @Override
    public void handle(BeDamageEvent event) {
        if (event.getType().isCounter() || event.getType().isBuff())
            return;
        if (RandUtil.success(getPct(event.getTarget()))) {
            log.info(Msg.trigger(getSelf(), Zheng.this));
            Runner cur = event.getType().isZhenNv() ? zhenNv : common;
            log.info(Msg.info(getSelf(), "更新", cur));
            cur.target = event.getTarget();
            getController().addAction(1000, cur);
        }
    }

    @Override
    public boolean sealed() {
        return getSelf().mitamaSealed();
    }

    class Runner implements Callback, Named {
        private final String name;
        Entity target;

        Runner(String name) {
            this.name = name;
        }

        @Override
        public void call() {
            final Self self = getSelf();
            if (self.isDead())
                return;
            log.info(Msg.info(self, name));
            Entity target = self.applyControl(this.target);
            if (target == null)
                return;
            self.getCommonAttack().counter(target);
            self.getEventController().trigger(new AfterActionEvent(self));
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
