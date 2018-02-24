package com.sine.yys.skill;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Skill;

import java.util.logging.Logger;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存，所属式神的引用。
 */
public abstract class BaseSkill implements Skill {
    protected static final String CD = "CD";
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    private boolean prepared = false;  // 用于处理buff回合数衰减。调用beforeAction()后为true。在2个状态之间转换

    @Override
    public final int getCD(Entity self) {
        return self.get(this.getClass(), CD, 0);
    }

    @Override
    public int getMAXCD() {
        return 0;
    }

    @Override
    public final void beforeAction(Controller controller, Entity self) {
        if (prepared) {
            log.severe("异常调用beforeAction()");
            return;
        }
        prepared = true;
        doBeforeAction(controller, self);
    }

    @Override
    public final int afterAction(Controller controller, Entity self) {
        if (!prepared)
            return getCD(self);
        prepared = false;
        int cd = 0;
        if (getMAXCD() > 0) {
            cd = getCD(self);
            if (cd > 0) {
                cd -= 1;
                self.put(this.getClass(), CD, cd);
            }
        }
        doAfterAction(controller, self);
        return cd;
    }

    protected void doBeforeAction(Controller controller, Entity self) {
    }

    protected void doAfterAction(Controller controller, Entity self) {
    }

    @Override
    public void init(Controller controller, Entity self) {
    }
}
