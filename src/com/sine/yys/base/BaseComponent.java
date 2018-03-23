package com.sine.yys.base;

import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.*;

import java.util.logging.Logger;

/**
 * 技能和御魂的通用逻辑。
 * 初始化时设定上下文。
 * 可以定义初始化、入场和死亡的操作。
 */
public class BaseComponent implements Component {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private Self self = null;
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
    protected final Self getSelf() {
        return self;
    }

    protected final Controller getController() {
        return controller;
    }

    /**
     * @return 进场监听器（进入战场或复活）。
     */
    protected EventHandler<EnterEvent> getEnterHandler() {
        return null;
    }

    /**
     * @return 死亡监听器（死亡）。
     */
    protected EventHandler<DieEvent> getDieHandler() {
        return null;
    }

    @Override
    public final void init(Controller controller, Self self, Camp own) {
        if (this.self != null) {
            log.warning("重复调用 init()。即将返回。");
            return;
        }
        this.controller = controller;
        this.self = self;
        this.own = own;
        this.enemy = this.own.getOpposite();

        final EventHandler<DieEvent> dieHandler = getDieHandler();
        if (dieHandler != null)
            self.getEventController().add(DieEvent.class, dieHandler);
        final EventHandler<EnterEvent> enterHandler = getEnterHandler();
        if (enterHandler != null)
            self.getEventController().add(EnterEvent.class, enterHandler);

        doInit(controller, self);
    }

    /**
     * 初始化操作。
     */
    public void doInit(Controller controller, Entity self) {
    }
}
