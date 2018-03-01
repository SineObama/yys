package com.sine.yys.skill.passive;

import com.sine.yys.info.Sealable;
import com.sine.yys.skill.BaseSkill;

/**
 * 通用实现：检查是否有封印被动效果。
 */
public abstract class BasePassiveSkill extends BaseSkill implements PassiveSkill, Sealable {
    @Override
    public boolean sealed() {
        return getSelf().getBuffController().passiveSealed();
    }

    class SealablePassiveHandler implements Sealable {
        @Override
        public boolean sealed() {
            return getSelf().getBuffController().passiveSealed();
        }
    }
}
