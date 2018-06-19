package com.sine.yys.operation;

import com.sine.yys.inter.*;
import com.sine.yys.util.Helper;

import java.util.List;
import java.util.Map;

/**
 * 简单的治疗式神AI，在有队友低于一定血量时使用指定技能。
 */
public class SimpleCureHandler<T extends ActiveSkill> extends AutoOperationHandler {
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
        ShikigamiEntity shikigamiEntity = own.getLeastLifeShikigami();
        final T t = Helper.findKeyBySubClassAndRemove(map, cureSkillClass);
        if (t != null && shikigamiEntity.getLife() < lifePct)
            return new OperationImpl(needTarget ? shikigamiEntity : null, t);
        return super.handle(self, own, map);
    }
}
