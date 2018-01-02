package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

public class ActionContextImpl implements ActionContext {
    private Entity self, target;
    private Camp own, enemy;

    public ActionContextImpl(Entity self, Entity target, Camp own, Camp enemy) {
        this.self = self;
        this.target = target;
        this.own = own;
        this.enemy = enemy;
    }

    @Override
    public Entity getSelf() {
        return self;
    }

    @Override
    public Entity getTarget() {
        return target;
    }

    @Override
    public Camp getOwn() {
        return own;
    }

    @Override
    public Camp getEnemy() {
        return enemy;
    }
}
