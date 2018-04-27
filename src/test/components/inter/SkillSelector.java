package test.components.inter;

import com.sine.yys.inter.ActiveSkill;

import java.util.Set;

@FunctionalInterface
public interface SkillSelector {
    ActiveSkill select(Set<ActiveSkill> skills);
}
