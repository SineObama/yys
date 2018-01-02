package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

public class PositionImpl implements Position {
    private final Entity source;
    private Entity current;

    @Override
    public Entity getSource() {
        return source;
    }

    @Override
    public boolean isDead() {
        return current != source;
    }

    @Override
    public void setDead(boolean dead) {
        this.current = null;
    }

    @Override
    public Entity getCurrent() {
        return current;
    }

    public PositionImpl(Entity source) {
        this.current = source;
        this.source = source;
    }
}
