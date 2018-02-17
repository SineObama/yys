package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 保存了所属式神的引用。
 */
public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    private Entity self;

    @Override
    public final void init(InitContext context) {
        this.self = context.getSelf();
        doInit(context);
    }

    protected void doInit(InitContext context) {
    }

    @Override
    public final Entity getSelf() {
        return self;
    }
}
