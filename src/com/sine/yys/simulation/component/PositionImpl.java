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

    @Override
    public Entity getSource() {
        return source;
    }

    @Override
    public void setCurrent(Entity entity) {
        current = entity;
    }

    @Override
    public Entity getCurrent() {
        return current;
    }
}
