package com.sine.yys.simulation.component.core.skill;

import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.info.Sealable;

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
