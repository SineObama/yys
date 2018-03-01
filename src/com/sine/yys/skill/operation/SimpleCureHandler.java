package com.sine.yys.skill.operation;

import com.sine.yys.inter.*;

import java.util.List;
import java.util.Map;

/**
 * 简单的治疗式神AI，在有队友低于一定血量时使用指定技能。
 */
public class SimpleCureHandler<T> extends AutoOperationHandler implements OperationHandler {
    private final Class<T> cureSkillClass;
    private final double lifePct;

    public SimpleCureHandler(Class<T> cureSkillClass, double lifePct) {
        this.cureSkillClass = cureSkillClass;
        this.lifePct = lifePct;
    }

    @Override
    public Operation handle(Entity self, Camp own, Map<ActiveSkill, List<? extends Entity>> map) {
        for (ActiveSkill activeSkill : map.keySet()) {
            if (cureSkillClass.isAssignableFrom(activeSkill.getClass())) {
                ShikigamiEntity shikigamiEntity = own.getLeastLifeShikigami();
                if (shikigamiEntity.getLife() < lifePct)
                    return new OperationImpl(shikigamiEntity, activeSkill);
                map.remove(activeSkill);
                break;
            }
        }
        return super.handle(self, own, map);
    }
}
