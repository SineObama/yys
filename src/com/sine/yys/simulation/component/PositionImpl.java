package com.sine.yys.simulation.component;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Position;

public class PositionImpl implements Position {
    private final Entity source;
    private Entity current;

    public PositionImpl(Entity source) {
        this.current = source;
        this.source = source;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Entity> T getSource() {
        return (T) source;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Entity> T getCurrent() {
        return (T) current;
    }

    @Override
    public void setCurrent(Entity entity) {
        current = entity;
    }
}
