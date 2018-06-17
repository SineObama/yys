package com.sine.yys.buff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.*;
import com.sine.yys.shikigami.operation.OperationImpl;
import com.sine.yys.util.RandUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 混乱。
 */
public class HunLuan extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan(Entity src) {
        super(1, "混乱", src);
    }

    /**
     * 使用普通攻击，随机攻击一个目标。
     */
    @Override
    public Operation resolve(Entity self, OperationHandler handler, Camp own, Camp enemy, CommonAttack commonAttack) {
        final List<Entity> allAlive = new ArrayList<>();
        allAlive.addAll(own.getAllAlive());
        allAlive.addAll(enemy.getAllAlive());
        allAlive.remove(self);
        return new OperationImpl(RandUtil.choose(allAlive), commonAttack);
    }
}
