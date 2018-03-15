package com.sine.yys.mitama;

import com.sine.yys.inter.*;
import com.sine.yys.util.JSON;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 使用嵌套类作为事件监听器。
 * 御魂不再与式神一一关联，只在初始化时把式神传递给监听器。
 */
public abstract class BaseMitama implements Mitama, Sealable, JSONable {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private Entity self = null;
    private Controller controller = null;
    private Camp own, enemy;

    protected final Camp getOwn() {
        return own;
    }

    protected final Camp getEnemy() {
        return enemy;
    }

    protected final Entity getSelf() {
        return self;
    }

    protected final Controller getController() {
        return controller;
    }

    @Override
    public void init(Controller controller, Entity self) {
        if (this.self != null) {
            log.warning("重复调用 Mitama.doInit()。即将返回。");
            return;
        }
        this.controller = controller;
        this.self = self;
        this.own = controller.getCamp(self);
        this.enemy = this.own.getOpposite();
        doInit(controller, self);
    }

    public void doInit(Controller controller, Entity self) {
    }

    @Override
    public boolean sealed() {
        return self.mitamaSealed();
    }

    @Override
    public String toJSON() {
        return JSON.format("name", getName());
    }
}
