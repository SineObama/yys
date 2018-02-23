package com.sine.yys.skill;

/**
 * 攻击技能。
 */
public interface AttackSkill extends ActiveSkill {
    /**
     * 技能系数。
     * 仅仅提供一个比较通用的系数接口。部分技能可能有较复杂的逻辑，包含多个系数。
     *
     * @return 系数。1表示100%。
     */
    double getCoefficient();
}
