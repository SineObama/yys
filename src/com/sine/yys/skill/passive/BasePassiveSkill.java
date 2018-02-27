package com.sine.yys.skill.passive;

import com.sine.yys.info.Sealable;
import com.sine.yys.inter.Entity;
import com.sine.yys.skill.BaseSkill;

/**
 * 通用实现：检查是否有封印被动效果。
 */
public abstract class BasePassiveSkill extends BaseSkill implements PassiveSkill {
    class SealablePassiveHandler implements Sealable {
        protected Entity self;

        SealablePassiveHandler(Entity self) {
            this.self = self;
        }

        @Override
        public boolean sealed() {
            return self.getBuffController().passiveSealed();
        }
    }
}
