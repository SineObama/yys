package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.InitContext;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 保存了所属式神的引用。
 */
public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    private Entity self;

    @Override
    public boolean isEnable() {
        return !self.getBuffController().mitamaSealed();
    }

    @Override
    public final void init(InitContext context) {
        this.self = context.getSelf();
        doInit(context);
    }

    protected void doInit(InitContext context) {
    }

    public final Entity getSelf() {
        return self;
    }
}
