package com.sine.yys.simulation.component.model.skill;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;

import java.util.List;

/**
 * 青行灯-吸魂灯。
 */
public class XiHunDeng extends SimpleGroupAttack {
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
    public double getPct() {
        return 0.3;
    }

    @Override
    public void doApply(Controller controller, Entity self, Entity target) {
        List<Entity> allAlive = controller.getCamp(self).getOpposite().getAllAlive();// XXX 未来对于召唤物的吸火检查
        super.doApply(controller, self, target);
        for (Entity entity : allAlive) {// XXX 对于已死目标是否会有问题
            controller.randomGrab(getPct(), entity);
        }
    }
}
