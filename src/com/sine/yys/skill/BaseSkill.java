package com.sine.yys.skill;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存，所属式神的引用。
 */
public abstract class BaseSkill implements Skill {
    protected static final String CD = "CD";
    protected final Logger log = Logger.getLogger(this.getClass().toString());

    @Override
    public final int getCD(Entity self) {
        return self.get(this.getClass(), CD, 0);
    }

    @Override
    public int getMAXCD() {
        return 0;
    }

    @Override
    public int step(Entity self) {
        int cd = 0;
        if (getMAXCD() > 0) {
            cd = self.get(this.getClass(), CD, 0);
            if (cd > 0) {
                cd -= 1;
                self.put(this.getClass(), CD, cd);
            }
        }
        return cd;
    }

    @Override
    public void init(Controller controller, Entity self) {
    }
}
