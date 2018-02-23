package com.sine.yys.simulation.component;

import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventController;
import com.sine.yys.inter.Position;
import com.sine.yys.util.RandUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * PVP阵营。
 * 初始化时给式神设置唯一的鬼火仓库（自己）。
 */
public abstract class BaseCamp implements Camp {
    protected final Logger log = Logger.getLogger(getClass().toString());

    private final EventController eventController = new EventControllerImpl();
    private final String name;
    private final String fullName;
    private final List<PositionImpl> positions = new ArrayList<>();
    private Camp opposite;

    public BaseCamp(String name) {
        this.name = name;
        this.fullName = "[" + name + "]";
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getFullName() {
        return fullName;
    }

    public final void addEntity(EntityImpl entity) {
        positions.add(new PositionImpl(entity));
    }

    @Override
    public final List<Entity> getAllAlive() {
        List<Entity> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() != null)
                entities.add(position.getCurrent());
        }
        return entities;
    }

    public final List<EntityImpl> getAllAlive2() {
        List<EntityImpl> entities = new ArrayList<>();
        for (PositionImpl position : positions) {
            if (position.getCurrent() != null)
                entities.add((EntityImpl) position.getCurrent());
        }
        return entities;
    }

    @Override
    public final List<Entity> getAllShikigami() {
        List<Entity> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() instanceof ShikigamiEntityImpl)
                entities.add(position.getCurrent());
        }
        return entities;
    }

    protected final List<ShikigamiEntityImpl> getAllShikigami2() {
        List<ShikigamiEntityImpl> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() instanceof ShikigamiEntityImpl)
                entities.add((ShikigamiEntityImpl) position.getCurrent());
        }
        return entities;
    }

    @Override
    public final boolean contain(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return true;
        }
        return false;
    }

    @Override
    public final Position getPosition(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return position;
        }
        return null;
    }

    @Override
    public final EventController getEventController() {
        return eventController;
    }

    public void init(Camp enemy) {
        opposite = enemy;
        for (ShikigamiEntityImpl shikigami : getAllShikigami2()) {
            shikigami.setCamp(this);
        }
    }

    @Override
    public final Entity randomTarget() {
        return RandUtil.choose(getAllAlive());
    }

    @Override
    public final Camp getOpposite() {
        return opposite;
    }
}
