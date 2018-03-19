package com.sine.yys.base;

import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.*;

import java.util.logging.Logger;

/**
 * 技能和御魂的通用逻辑。
 * 初始化时设定上下文、可以定义初始化操作、入场和死亡的回调操作。
 */
public class BaseComponent implements Component {
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

    /**
     * 在进入战场或复活时触发。
     * 子类可以重写以添加事件监听等。
     */
    protected EventHandler<EnterEvent> getEnterHandler() {
        return null;
    }

    /**
     * 在自身死亡事件时触发。
     * 子类可以重写以移除事件监听等。
     */
    protected EventHandler<DieEvent> getDieHandler() {
        return null;
    }

    @Override
    public final void init(Controller controller, Entity self) {
        if (this.self != null) {
            log.warning("重复调用 init()。即将返回。");
            return;
        }
        this.controller = controller;
        this.self = self;
        this.own = controller.getCamp(self);
        this.enemy = this.own.getOpposite();

        final EventHandler<DieEvent> dieHandler = getDieHandler();
        if (dieHandler != null)
            self.getEventController().add(DieEvent.class, dieHandler);
        final EventHandler<EnterEvent> enterHandler = getEnterHandler();
        if (enterHandler != null)
            self.getEventController().add(EnterEvent.class, enterHandler);

        doInit(controller, self);
    }

    public void doInit(Controller controller, Entity self) {
    }
}
