package test.components.inter;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;

import java.util.List;

@FunctionalInterface
public interface TargetSelector {
    Entity select(Entity self, Camp own, ActiveSkill skill, List<? extends Entity> entities);
}
