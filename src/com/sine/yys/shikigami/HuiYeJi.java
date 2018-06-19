package com.sine.yys.shikigami;

import com.sine.yys.inter.*;
import com.sine.yys.operation.AutoOperationHandler;
import com.sine.yys.operation.OperationImpl;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.LongShouZhiYu;
import com.sine.yys.skill.commonattack.PengLaiYuZhi;
import com.sine.yys.skill.passive.HuoShuQiu;
import com.sine.yys.util.Helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 辉夜姬。
 */
public class HuiYeJi extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new PengLaiYuZhi(), new HuoShuQiu(), new LongShouZhiYu());
    }

    @Override
    public OperationHandler getAI() {
        return new HuiYeJiAI();
    }

    @Override
    public double getOriginAttack() {
        return 2332;
    }

    @Override
    public String getName() {
        return "辉夜姬";
    }

    /**
     * 如果没有幻境或幻境即将消失（回合数为1），则使用龙首之玉技能。否则（除去龙首之玉技能）交由父类处理……
     */
    class HuiYeJiAI extends AutoOperationHandler {
        @Override
        public Operation handle(Entity self, Camp own, Map<ActiveSkill, List<? extends Entity>> map) {
            LongShouZhiYu.Buff buff = self.getBuffController().get(LongShouZhiYu.Buff.class);
            final LongShouZhiYu longShouZhiYu = Helper.findKeyBySubClassAndRemove(map, LongShouZhiYu.class);
            if (longShouZhiYu != null && (buff == null || buff.getLast() <= 1))
                return new OperationImpl(null, longShouZhiYu);
            return super.handle(self, own, map);
        }
    }
}
