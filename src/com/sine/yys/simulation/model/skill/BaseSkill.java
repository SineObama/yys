package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Initable;
import com.sine.yys.simulation.model.entity.BaseEntity;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存，所属式神的引用。
 */
public abstract class BaseSkill implements Skill, Initable {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    private final int MAXCD;
    private BaseEntity self;
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
    public final int getMAXCD() {
        return MAXCD;
    }

    @Override
    public final int getCD() {
        return CD;
    }

    @Override
    public int step() {
        if (this.CD == 0)
            return 0;
        return this.CD = this.CD - 1;
    }

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
