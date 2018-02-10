package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 初始化时所用的上下文对象。
 * 比如在上下文设置当前式神，然后把上下文传递给技能，让技能设置自己所属的式神。
 */
public class InitContext {
    private Camp own, enemy;
    private Entity self;

    public Camp getOwn() {
        return own;
    }

    public void setOwn(Camp own) {
        this.own = own;
    }

    public Camp getEnemy() {
        return enemy;
    }

    public void setEnemy(Camp enemy) {
        this.enemy = enemy;
    }

    public Entity getSelf() {
        return self;
    }

    public void setSelf(Entity self) {
        this.self = self;
    }
}
