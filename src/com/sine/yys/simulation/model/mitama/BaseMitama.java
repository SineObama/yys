package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.logging.Logger;

/**
 * 御魂通用逻辑。
 */
public abstract class BaseMitama implements Mitama {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    private Entity self;

    /**
     * 注入实体，子类记得调用。
     */
    @Override
    public void init(InitContext context) {
        this.self = context.getSelf();
    }

    @Override
    public Entity getSelf() {
        return self;
    }
}
