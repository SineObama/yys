package com.sine.yys.simulation.model.skill;

/**
 * 攻击技能。
 * 提供一个比较通用的系数接口。但部分技能可能有较复杂的逻辑，多个系数。
 */
public interface AttackSkill extends ActiveSkill {
    double getCoefficient();
}
