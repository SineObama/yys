package com.sine.yys.mitama;

import com.sine.yys.event.BeDamageEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.Self;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.inter.base.Named;
import com.sine.yys.inter.base.Sealable;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 狰。
 * <p>
 * 椒图传递的伤害可以触发。（薙魂可以触发。）
 * 被混乱和沉默可以正常反击；被嘲讽反击嘲讽对象；其他控制下不可反击。
 */
public class Zheng extends BaseMitama {
    @Override
    public String getName() {
        return "狰";
    }

    public double getPct() {
        return 0.35;
    }

    class BeDamageHandler implements EventHandler<BeDamageEvent>, Sealable {
        private Runner common = new Runner("普通狰反击");
        private Runner zhenNv = new Runner("针女狰反击");

        @Override
        public void handle(BeDamageEvent event) {
            if (event.getType().isCounter())
                return;
            if (RandUtil.success(getPct())) {
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
                Entity target = self.applyControl(this.target);
                if (target == null)
                    return;
                self.getCommonAttack().counter(target);
            }

            @Override
            public String getName() {
                return name;
            }
        }
    }
}
