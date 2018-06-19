package com.sine.yys.buff.control;

import com.sine.yys.inter.*;
import com.sine.yys.operation.OperationImpl;

/**
 * 嘲讽，标记接口。
 * <p>
 * 若目标已死（一般是狸猫），则随机普攻敌方目标。
 */
public interface ChaoFeng extends ControlBuff {
    @Override
    default Operation resolve(Entity self, OperationHandler handler, Camp own, Camp enemy, CommonAttack commonAttack) {
        Entity target = getSrc();
        if (target.isDead())
            target = enemy.randomTarget();
        return new OperationImpl(target, commonAttack);
    }
}
