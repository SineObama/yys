package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.InitContext;
import com.sine.yys.simulation.model.Initable;
import com.sine.yys.simulation.model.Mitama;
import com.sine.yys.simulation.model.entity.BaseEntity;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 * 保存了所属式神的引用。
 */
public abstract class BaseMitama implements Mitama, Initable {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    private BaseEntity self;

    @Override
    public final void init(InitContext context) {
        this.self = (BaseEntity) context.getSelf();
        doInit(context);
    }

    protected void doInit(InitContext context) {
    }

    public final BaseEntity getSelf() {
        return self;
    }
}
