package com.sine.yys.shikigami.operation;

import com.sine.yys.inter.*;

import java.util.List;
import java.util.Map;

/**
 * 简单的治疗式神AI，在有队友低于一定血量时使用指定技能。
 */
public class SimpleCureHandler<T> extends AutoOperationHandler implements OperationHandler {
    private final Class<T> cureSkillClass;
    private final double lifePct;
    private final boolean needTarget;

    /**
     * @param needTarget 技能是否需要指定目标。一般全体治疗不需要。
     */
    public SimpleCureHandler(Class<T> cureSkillClass, double lifePct, boolean needTarget) {
        this.cureSkillClass = cureSkillClass;
        this.lifePct = lifePct;
        this.needTarget = needTarget;
    }

    @Override
    public Operation handle(Entity self, Camp own, Map<ActiveSkill, List<? extends Entity>> map) {
        for (ActiveSkill activeSkill : map.keySet()) {
            if (cureSkillClass.isAssignableFrom(activeSkill.getClass())) {
                ShikigamiEntity shikigamiEntity = own.getLeastLifeShikigami();
                if (shikigamiEntity.getLife() < lifePct)
                    return new OperationImpl(needTarget ? shikigamiEntity : null, activeSkill);
                map.remove(activeSkill);
                break;
            }
        }
        return super.handle(self, own, map);
    }
}
