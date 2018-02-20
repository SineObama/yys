package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.Skill;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存，所属式神的引用。
 */
public abstract class BaseSkill implements Skill {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    protected int CD = 0; // XXXX CD设置方式？

    @Override
    public String getDetail() {
        return "";
    }

    @Override
    public int getMAXCD() {
        return 0;
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
    public void init(InitContext context) {
    }
}
