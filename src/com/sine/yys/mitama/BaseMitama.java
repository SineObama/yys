package com.sine.yys.mitama;

import com.sine.yys.info.Sealable;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Mitama;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 使用嵌套类作为事件监听器。
 * 御魂不再与式神一一关联，只在初始化时把式神传递给监听器。
 */
public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void init(Controller controller, Entity self) {
    }

    class SealableMitamaHandler implements Sealable {
        protected final Entity self;

        SealableMitamaHandler(Entity self) {
            this.self = self;
        }

        @Override
        public boolean sealed() {
            return self.getBuffController().mitamaSealed();
        }
    }
}
