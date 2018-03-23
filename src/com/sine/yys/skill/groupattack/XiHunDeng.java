package com.sine.yys.skill.groupattack;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.PctEffect;

import java.util.List;

/**
 * 青行灯-吸魂灯。
 */
public class XiHunDeng extends SimpleGroupAttack implements PctEffect {
    @Override
    public void doApply(Entity target) {
        final Controller controller = getController();
        final Entity self = getSelf();
        List<? extends Entity> allAlive = getEnemy().getAllAlive();// XXX 未来对于召唤物的吸火检查
        super.doApply(target);
        for (Entity entity : allAlive)  // XXX 吸魂灯吸火对于已死目标是否会有问题
            controller.randomGrab(self, entity, getPct());
    }

    @Override
    public String getName() {
        return "吸魂灯";
    }

    @Override
    public double getCoefficient() {
        return 1.58 * 1.2;
    }

    @Override
    public int getTimes() {
        return 1;
    }

    /**
     * 吸火概率
     */
    @Override
    public double getPct() {
        return 0.3;
    }
}
