package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.InitContext;
import com.sine.yys.simulation.component.model.Initable;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存，所属式神的引用。
 */
public abstract class BaseSkill implements Skill, Initable {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    private final int MAXCD;
    private Entity self;
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
        this.self = context.getSelf();
        doInit(context);
    }

    protected void doInit(InitContext context) {
    }

    public final Entity getSelf() {
        return self;
    }
}
