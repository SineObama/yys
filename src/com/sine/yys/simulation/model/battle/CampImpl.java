package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.entity.Shikigami;

import java.util.ArrayList;
import java.util.List;

public class CampImpl implements Camp {
    private final String name;
    private List<Position> positions = new ArrayList<>();
    private int fire;
    private int fireBarPos = 0;

    @Override
    public String getName() {
        return name;
    }

    public CampImpl(String name, int fire) {
        this.name = name;
        this.fire = fire;
    }

    @Override
    public void addEntity(Entity entity) {
        positions.add(new PositionImpl(entity));
    }

    @Override
    public List<Entity> getAllAlive() {
        List<Entity> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() != null)
                entities.add(position.getCurrent());
        }
        return entities;
    }

    @Override
    public List<Shikigami> getAllShikigami() {
        List<Shikigami> entities = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCurrent() instanceof Shikigami)
                entities.add((Shikigami) position.getCurrent());
        }
        return entities;
    }

    @Override
    public boolean contain(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return true;
        }
        return false;
    }

    @Override
    public int getFire() {
        return fire;
    }

    @Override
    public void useFire(int count) {
        fire -= count;
    }

    @Override
    public int grabFire(int count) {
        if (count > fire)
            count = fire;
        fire -= count;
        return count;
    }

    @Override
    public void addFire(int count) {
        fire += count;
        if (fire > 8)
            fire = 8;
    }

    @Override
    public int step() {
        fireBarPos += 1;
        if (fireBarPos > 5)
            fireBarPos = 1;
        return fireBarPos;
    }

    @Override
    public Position getPosition(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return position;
        }
        return null;
    }
}
