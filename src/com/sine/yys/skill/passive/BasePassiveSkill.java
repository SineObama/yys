package com.sine.yys.skill.passive;

import com.sine.yys.inter.base.Sealable;
import com.sine.yys.skill.BaseSkill;

/**
 * 被动技能。
 */
public abstract class BasePassiveSkill extends BaseSkill implements Sealable {
    @Override
    public final boolean sealed() {
        return getSelf().passiveSealed();
    }
}
