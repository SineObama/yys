package test.components.inter;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;

import java.util.Set;

@FunctionalInterface
public interface SkillSelector {
    ActiveSkill select(Entity self, Camp own, Set<ActiveSkill> skills);
}
