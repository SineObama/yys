package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 */
public abstract class BaseSkill implements Skill {
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    private Entity self;
    private final int MAXCD;
    private int CD = 0;

    public BaseSkill() {
        this.MAXCD = 0;
    }

    public BaseSkill(int MAXCD) {
        this.MAXCD = MAXCD;
    }

    @Override
    public String getDetail() {
        return "";
    }

    @Override
    public int getMAXCD() {
        return MAXCD;
    }

    @Override
    public int getCD() {
        return CD;
    }

    @Override
    public int step() {
        if (this.CD == 0)
            return 0;
        return this.CD = this.CD - 1;
    }

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
