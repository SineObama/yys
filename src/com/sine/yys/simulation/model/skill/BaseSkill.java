package com.sine.yys.simulation.model.skill;

import java.util.logging.Logger;

public abstract class BaseSkill implements Skill {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
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
}
