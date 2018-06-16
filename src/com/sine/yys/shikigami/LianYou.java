package com.sine.yys.shikigami;

import com.sine.yys.inter.*;
import com.sine.yys.shikigami.operation.AutoOperationHandler;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.XiongDiZhiBan;
import com.sine.yys.skill.commonattack.PangZou;
import com.sine.yys.skill.passive.RenDuoShiZhong;
import com.sine.yys.util.Helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 镰鼬。
 */
public class LianYou extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new PangZou(), new RenDuoShiZhong(), new XiongDiZhiBan());
    }

    @Override
    public OperationHandler getAI() {
        return new AutoOperationHandler() {
            @Override
            public Operation handle(Entity self, Camp own, Map<ActiveSkill, List<? extends Entity>> map) {
                for (Entity entity : own.getAllAlive()) {
                    if (entity.getBuffController().contain(XiongDiZhiBan.Attack.class) || entity.getBuffController().contain(XiongDiZhiBan.EffectDef.class)) {
                        Helper.findKeyBySubClassAndRemove(map, XiongDiZhiBan.class);
                        break;
                    }
                }
                return super.handle(self, own, map);
            }
        };
    }

    @Override
    public double getOriginAttack() {
        return 2680;
    }

    @Override
    public String getName() {
        return "镰鼬";
    }
}
