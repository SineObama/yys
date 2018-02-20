package com.sine.yys.simulation.component.core;

import com.sine.yys.simulation.component.core.Camp;
import com.sine.yys.simulation.component.core.FireRepo;
import com.sine.yys.simulation.component.inter.Entity;

/**
 * 阵营、式神、技能、御魂等对象进行战斗初始化时所用的上下文对象。
 * 比如在上下文设置当前式神，然后把对象传递给技能，由技能自己设置所属的式神。
 */
public class InitContext {
    private Camp own, enemy;
    private Entity self;
    private FireRepo fireRepo;

    public FireRepo getFireRepo() {
        return fireRepo;
    }

    public void setFireRepo(FireRepo fireRepo) {
        this.fireRepo = fireRepo;
    }

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
