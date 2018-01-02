package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.ActionContext;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 青行灯-幽光。
 */
public class YouGuang extends CommonAttack {
    @Override
    public String getName() {
        return "幽光";
    }

    @Override
    public String getDetail() {
        return "";
    }

    /**
     * 吸火概率
     */
    public double getPct() {
        return 0.3;
    }

    @Override
    public void apply(ActionContext context) {
        super.apply(context);
        super.randomGrab(context, getPct(), 1);
    }
}
