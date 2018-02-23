package com.sine.yys.effect;

import java.util.logging.Logger;

public abstract class BaseEffect implements Effect {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    private final String name;

    BaseEffect(String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }
}
