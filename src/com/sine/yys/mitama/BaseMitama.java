package com.sine.yys.mitama;

import com.sine.yys.info.Sealable;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 使用嵌套类作为事件监听器。
 * 御魂不再与式神一一关联，只在初始化时把式神传递给监听器。
 */
public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    public void init(Controller controller) {
    }

    class SealableMitamaHandler implements Sealable {
        protected final Entity self;
        protected final Controller controller;

        SealableMitamaHandler(Controller controller) {
            this.controller = controller;
            this.self = controller.getSelf();
        }

        @Override
        public boolean sealed() {
            return self.getBuffController().mitamaSealed();
        }
    }
}
