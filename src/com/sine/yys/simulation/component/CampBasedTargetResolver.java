package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.CommonAttack;

import java.util.ArrayList;
import java.util.List;

public class CampBasedTargetResolver implements TargetResolver {
    private final Camp own, enemy;

    public CampBasedTargetResolver(Camp own, Camp enemy) {
        this.own = own;
        this.enemy = enemy;
    }
    @Override
    public List<Entity> resolve(ActiveSkill skill) {
        if (skill instanceof CommonAttack)
            return enemy.getAllEntity();
        return new ArrayList<>();
    }
}
