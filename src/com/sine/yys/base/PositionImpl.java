package com.sine.yys.base;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Position;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.util.JSON;

public class PositionImpl implements Position, JSONable {
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

    @Override
    public String toString() {
        return current.toString();
    }

    @Override
    public String toJSON() {
        if (source == current)
            return JSON.format("cur", current);
        return JSON.format("src", source, "cur", current);
    }
}
