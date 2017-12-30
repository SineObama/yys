package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 技能。根基类。
 */
public interface Skill {
    String getName();
    String getDetail();
    int getCD();

    void doDamage(Entity self, Entity target);
}
