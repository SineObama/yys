package com.sine.yys.skill;

import com.sine.yys.event.DieEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.*;
import com.sine.yys.util.JSON;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存，所属式神的引用。
 */
public abstract class BaseSkill implements Skill, JSONable {
    protected static final String CD = "CD";
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private boolean prepared = false;  // 用于处理buff回合数衰减。调用beforeAction()后为true。在2个状态之间转换
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
    public final int getCD() {
        return self.get(this.toString() + CD, 0);
    }

    public final void setCD(int cd) {
        self.put(this.toString() + CD, cd);
    }

    @Override
    public int getMAXCD() {
        return 0;
    }

    @Override
    public final int beforeAction() {
        doBeforeAction();
        if (prepared) {
            log.warning("异常调用beforeAction()");
            return getCD();
        }
        if (getCD() > 0)
            prepared = true;
        return getCD();
    }

    @Override
    public final int afterAction() {
        doAfterAction();
        if (!prepared)
            return getCD();
        prepared = false;
        int cd = 0;
        if (getMAXCD() > 0) {
            cd = getCD();
            if (cd > 0) {
                cd -= 1;
                setCD(cd);
            }
        }
        return cd;
    }

    protected void doBeforeAction() {
    }

    protected void doAfterAction() {
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
    public void init(Controller controller, Entity self) {
        if (this.self != null) {
            log.warning("重复调用 Skill.doInit()。即将返回。");
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

    @Override
    public String toJSON() {
        return JSON.format("name", getName(), "CD", getCD());
    }

    protected class SealablePassiveHandler implements Sealable {
        @Override
        public boolean sealed() {
            return getSelf().passiveSealed();
        }
    }
}
