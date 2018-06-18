package com.sine.yys.impl;

import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

import java.util.logging.Logger;

/**
 * 技能和御魂的通用逻辑，需要进行初始化的逻辑组件。
 * 初始化时设定上下文。
 * 可以定义初始化、入场和死亡的操作。
 */
public class BaseComponent {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private Entity self = null;
    private Controller controller = null;
    private Camp own, enemy;

    /**
     * @return 自身阵营。
     */
    protected final Camp getOwn() {
        return own;
    }

    /**
     * @return 敌对阵营。
     */
    protected final Camp getEnemy() {
        return enemy;
    }

    /**
     * @return 自身所属式神。
     */
    protected final Entity getSelf() {
        return self;
    }

    protected final Controller getController() {
        return controller;
    }

    /**
     * 在进场事件时被调用。
     */
    protected void onEnter() {
    }

    /**
     * 在死亡事件时被调用。
     */
    protected void onDie() {
    }

    /**
     * @param self 所属式神。
     * @param own  所在阵营。
     */
    public final void init(Controller controller, Entity self, Camp own) {
        if (this.self != null) {
            log.warning("重复调用 init()。即将返回。");
            return;
        }
        this.controller = controller;
        this.self = self;
        this.own = own;
        this.enemy = this.own.getOpposite();

        self.getEventController().add(EnterEvent.class, new EnterHandler());
        self.getEventController().add(DieEvent.class, new DieHandler());

        doInit(controller, self);
    }

    class EnterHandler implements EventHandler<EnterEvent> {
        @Override
        public void handle(EnterEvent event) {
            onEnter();
        }
    }

    class DieHandler implements EventHandler<DieEvent> {
        @Override
        public void handle(DieEvent event) {
            onDie();
        }
    }

    /**
     * 初始化操作。
     */
    public void doInit(Controller controller, Entity self) {
    }
}
