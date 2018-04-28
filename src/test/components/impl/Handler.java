package test.components.impl;

import com.sine.yys.inter.*;
import com.sine.yys.shikigami.operation.OperationImpl;
import test.components.inter.SkillSelector;
import test.components.inter.TargetSelector;

import java.util.List;
import java.util.Map;

/**
 * 提供分别对技能和目标进行选择的方法。
 */
public class Handler implements OperationHandler {
    private SkillSelector skillSelector;
    private TargetSelector targetSelector;

    public void setSkillSelector(SkillSelector skillSelector) {
        this.skillSelector = skillSelector;
    }

    public void setTargetSelector(TargetSelector targetSelector) {
        this.targetSelector = targetSelector;
    }

    @Override
    public Operation handle(Entity self, Camp own, Map<ActiveSkill, List<? extends Entity>> map) {
        ActiveSkill skill = skillSelector.select(map.keySet());
        return new OperationImpl(targetSelector.select(map.get(skill)), skill);
    }
}