package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.effect.PassiveEffect;

/**
 * 被动技能
 */
public interface PassiveSkill extends Skill {
    PassiveEffect getEffect();
}
