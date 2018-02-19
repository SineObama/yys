package com.sine.yys.simulation.component.camp;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.model.*;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * PVP阵营。
 * 初始化时给式神设置唯一的鬼火仓库（自己）。
 */
public class PVPCamp implements Camp, FireRepo {
    private final Logger log = Logger.getLogger(this.getClass().toString());

    private final EventController eventController = new EventControllerImpl();
    private final String name;
    private final String fullName;
    private final List<Position> positions = new ArrayList<>();
    private int fire;
    private int fireBarPos = 0;
    private int increase = 3;
    private Camp opposite;

    public PVPCamp(String name, int fire) {
        this.name = name;
        this.fullName = "[" + name + "]";
        this.fire = fire;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return fullName;
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
    public int getIncrease() {
        return increase;
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
        log.info(Msg.info(this, "增加 " + count + " 点鬼火，当前剩余 " + fire + " 点"));
    }

    @Override
    public void step() {
        fireBarPos += 1;
        if (fireBarPos > 5) {
            fireBarPos = 1;
            log.info(Msg.info(this, "行动满5回合，将回复 " + increase + " 点鬼火"));
            addFire(increase);
            if (increase < 5)
                increase += 1;
        }
    }

    @Override
    public Position getPosition(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return position;
        }
        return null;
    }

    @Override
    public EventController getEventController() {
        return eventController;
    }

    @Override
    public void init(InitContext context) {
        opposite = context.getEnemy();
        context.setFireRepo(this);
        final List<Shikigami> allShikigami = getAllShikigami();
        for (Shikigami shikigami : allShikigami) {
            shikigami.init(context);
        }
    }

    @Override
    public Entity randomTarget() {
        return RandUtil.choose(getAllAlive());
    }

    @Override
    public Camp getOpposite() {
        return opposite;
    }
}
