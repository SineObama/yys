package com.sine.yys.buff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.*;
import com.sine.yys.shikigami.operation.OperationImpl;

import java.util.HashMap;
import java.util.List;

/**
 * 沉默。
 * 只能使用普通攻击。
 */
public class ChenMo extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public ChenMo(int last, Entity src) {
        super(last, "沉默", src);
    }

    @Override
    public Operation resolve(Entity self, OperationHandler handler, Camp own, Camp enemy, CommonAttack activeSkill) {
        final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(own, self);
        if (targets != null)
            return handler.handle(self, own, new HashMap<ActiveSkill, List<? extends Entity>>() {{
                put(activeSkill, targets);
            }});
        else
            return new OperationImpl(null, null);
    }
}
