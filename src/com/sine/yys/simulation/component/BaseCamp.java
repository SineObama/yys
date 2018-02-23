package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.inter.Camp;
import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.component.inter.Position;
import com.sine.yys.simulation.event.EventController;
import com.sine.yys.simulation.event.EventControllerImpl;
import com.sine.yys.simulation.util.RandUtil;

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

    public final void addEntity(BaseEntity entity) {
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

    public final List<BaseEntity> getAllAlive2() {
        List<BaseEntity> entities = new ArrayList<>();
        for (PositionImpl position : positions) {
            if (position.getCurrent() != null)
                entities.add((BaseEntity) position.getCurrent());
        }
        return entities;
    }

    @Override
    public final List<Entity> getAllShikigami() {
        List<Entity> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() instanceof Shikigami)
                entities.add(position.getCurrent());
        }
        return entities;
    }

    protected final List<Shikigami> getAllShikigami2() {
        List<Shikigami> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() instanceof Shikigami)
                entities.add((Shikigami) position.getCurrent());
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

    @Override
    public final EventController getEventController(Entity entity) {
        for (PositionImpl position : positions) {
            if (position.getCurrent() == entity)
                return position.getCurrent().getEventController();
        }
        return null;
    }

    public void init(Camp enemy) {
        opposite = enemy;
        for (Shikigami shikigami : getAllShikigami2()) {
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
