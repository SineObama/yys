package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.operation.SimpleCureHandler;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.HuaNiaoXiangWen;
import com.sine.yys.skill.commonattack.GuiNiao;
import com.sine.yys.skill.passive.HuaJing;

import java.util.Arrays;
import java.util.Collection;

/**
 * 花鸟卷。
 */
public class HuaNiaoJuan extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new GuiNiao(), new HuaJing(), new HuaNiaoXiangWen());
    }

    @Override
    public OperationHandler getAI() {
        return new SimpleCureHandler<>(HuaNiaoXiangWen.class, 0.6, false);
    }

    @Override
    public double getOriginAttack() {
        return 2305;
    }

    @Override
    public String getName() {
        return "花鸟卷";
    }
}
