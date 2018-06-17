package com.sine.yys.buff.control;

import com.sine.yys.inter.*;
import com.sine.yys.shikigami.operation.OperationImpl;

/**
 * 不可移动（行动）的控制效果，标记接口。
 */
public interface Unmovable extends ControlBuff {
    @Override
    default Operation resolve(Entity self, OperationHandler handler, Camp own, Camp enemy, CommonAttack commonAttack) {
        return new OperationImpl(null, null);
    }
}
