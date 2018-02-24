package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.debuff.Debuff;
import com.sine.yys.buff.debuff.PctDoT;
import com.sine.yys.buff.debuff.ReduceDefense;
import com.sine.yys.buff.debuff.XuanYun;
import com.sine.yys.info.PctEffect;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.RandUtil;

import java.util.Arrays;

/**
 * 镰鼬-胖揍。
 */
public class PangZou extends BaseCommonAttack implements PctEffect {
    @Override
    public String getName() {
        return "胖揍";
    }

    @Override
    public double getCoefficient() {
        return 1.0 * 1.2;
    }

    /**
     * @return 附加效果概率
     */
    @Override
    public double getPct() {
        return 0.2;
    }

    public Debuff getDebuff() {
        return RandUtil.choose(Arrays.asList(new ReduceDefense(2, "破防", 0.3), new XuanYun(), new PctDoT(2, "镰鼬持续伤害", 0.05)));
    }

    @Override
    protected void doApply(Controller controller, Entity self, Entity target) {
        super.doApply(controller, self, target);
        controller.applyDebuff(self, this, target, getDebuff());
    }
}
