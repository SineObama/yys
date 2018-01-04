package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

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
