package com.sine.yys.simulation.component.skill;

/**
 * 通用实现：检查是否有封印被动效果。
 */
public abstract class BasePassiveSkill extends BaseSkill implements PassiveSkill {
    @Override
    public boolean isEnable() {
        return !getSelf().getBuffController().passiveSealed();
    }
}
