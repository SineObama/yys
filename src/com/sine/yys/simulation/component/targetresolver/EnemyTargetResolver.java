package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.CommonAttack;

import java.util.ArrayList;
import java.util.List;

public class EnemyTargetResolver implements TargetResolver {
    @Override
    public List<Entity> resolve(Entity self, Camp own, Camp enemy) {
        return enemy.getAllEntity();
    }
}
