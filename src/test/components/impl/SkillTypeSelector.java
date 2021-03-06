package test.components.impl;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import test.components.inter.SkillSelector;

import java.util.Set;
import java.util.logging.Logger;

/**
 * 根据技能类型进行选择。
 */
public class SkillTypeSelector implements SkillSelector {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private Class<?> aClass;

    public SkillTypeSelector(Class<?> aClass) {
        this.aClass = aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    @Override
    public ActiveSkill select(Entity self, Camp own, Set<ActiveSkill> skills) {
        for (ActiveSkill skill : skills) {
            if (aClass.isAssignableFrom(skill.getClass()))
                return skill;
        }
        return null;
    }
}
