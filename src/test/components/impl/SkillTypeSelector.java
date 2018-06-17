package test.components.impl;

import com.sine.yys.inter.ActiveSkill;
import test.components.inter.SkillSelector;

import java.util.Set;
import java.util.logging.Logger;

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
    public ActiveSkill select(Set<ActiveSkill> skills) {
        for (ActiveSkill skill : skills) {
            if (aClass.isAssignableFrom(skill.getClass()))
                return skill;
        }
        return null;
    }
}
