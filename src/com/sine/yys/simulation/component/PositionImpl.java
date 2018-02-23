package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.Entity;

public class PositionImpl implements Position {
    private final BaseEntity source;
    private BaseEntity current;

    public PositionImpl(BaseEntity source) {
        this.current = source;
        this.source = source;
    }

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
        if (dead)
            this.current = null;
        else
            this.current = this.source;
    }

    @Override
    public Entity getCurrent() {
        return current;
    }

    public BaseEntity getCurrent2() {
        return current;
    }
}
