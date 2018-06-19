package test.components.impl;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Operation;
import com.sine.yys.operation.AutoOperationHandler;
import com.sine.yys.operation.OperationImpl;
import test.components.inter.SkillSelector;
import test.components.inter.TargetSelector;

import java.util.List;
import java.util.Map;

/**
 * 测试用，逻辑框架为分别选择技能和目标。
 */
public class Handler extends AutoOperationHandler {
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
        ActiveSkill skill = skillSelector.select(self, own, map.keySet());
        if (skill != null)
            return new OperationImpl(targetSelector.select(self, own, skill, map.get(skill)), skill);
        return super.handle(self, own, map);
    }
}
