package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.info.Sealable;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 使用嵌套类作为事件监听器。
 * 御魂不再与式神一一关联，只在初始化时把式神传递给监听器。
 */
public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    class SealableMitamaHandler implements Sealable {
        protected Entity self;

        SealableMitamaHandler(Entity self) {
            this.self = self;
        }

        @Override
        public boolean sealed() {
            return self.getBuffController().mitamaSealed();
        }
    }
}
