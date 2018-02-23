package com.sine.yys.simulation.component.shishen;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.buff.buff.LongShouZhiYuBuff;
import com.sine.yys.simulation.component.shishen.skill.ActiveSkill;
import com.sine.yys.simulation.component.shishen.skill.HuoShuQiu;
import com.sine.yys.simulation.component.shishen.skill.LongShouZhiYu;
import com.sine.yys.simulation.component.shishen.skill.PengLaiYuZhi;
import com.sine.yys.simulation.component.shishen.skill.operation.AutoOperationHandler;
import com.sine.yys.simulation.component.shishen.skill.operation.Operation;
import com.sine.yys.simulation.component.shishen.skill.operation.OperationHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 辉夜姬。
 */
public class HuiYeJi extends BaseShiShen {
    public HuiYeJi() {
        super(Arrays.asList(new PengLaiYuZhi(), new HuoShuQiu(), new LongShouZhiYu()), "辉夜姬");
    }

    @Override
    public OperationHandler getAI() {
        return new HuiYeJiAI();
    }

    /**
     * 如果没有幻境或幻境即将消失（回合数为1），则使用龙首之玉技能。否则（除去龙首之玉技能）交由父类处理……
     */
    class HuiYeJiAI extends AutoOperationHandler implements OperationHandler {
        @Override
        public Operation handle(Entity self, Map<ActiveSkill, List<? extends Entity>> map) {
            LongShouZhiYuBuff buff = self.getBuffController().getUnique(LongShouZhiYuBuff.class);
            for (ActiveSkill activeSkill : map.keySet()) {
                if (activeSkill instanceof LongShouZhiYu) {
                    if (buff == null || buff.getLast() <= 1) {
                        return new Operation(null, activeSkill);
                    } else {
                        map.remove(activeSkill);
                        break;
                    }
                }
            }
            return super.handle(self, map);
        }
    }
}
