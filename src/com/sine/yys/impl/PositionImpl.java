package com.sine.yys.impl;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Position;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.util.JSON;

public class PositionImpl<T extends Entity> implements Position<T>, JSONable {
    private final T source;
    private T current;

    public PositionImpl(T source) {
        this.current = source;
        this.source = source;
    }

    @Override
    public T getSource() {
        return source;
    }

    @Override
    public T getCurrent() {
        return current;
    }

    @Override
    public void setCurrent(T entity) {
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
