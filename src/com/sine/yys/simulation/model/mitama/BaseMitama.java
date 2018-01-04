package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;

import java.util.logging.Logger;

public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    @Override
    public void init(InitContext context) {
    }
}
