package com.sine.yys.skill;

import com.sine.yys.impl.BaseComponent;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.inter.base.Sealable;
import com.sine.yys.inter.Skill;
import com.sine.yys.util.JSON;

/**
 * 技能通用逻辑。
 * 包括CD的设定，战斗中CD的保存。
 */
public abstract class BaseSkill extends BaseComponent implements Skill, JSONable {
    private static final String CD = "CD";
    private boolean prepared = false;  // 用于处理buff回合数衰减。调用beforeAction()后为true。在2个状态之间转换

    @Override
    public final int getCD() {
        return getSelf().get(this.toString() + CD, 0);
    }

    final void setCD(int cd) {
        getSelf().put(this.toString() + CD, cd);
    }

    @Override
    public int getMAXCD() {
        return 0;
    }

    @Override
    public final int beforeAction() {
        doBeforeAction();
        if (prepared) {
            log.warning("异常调用beforeAction()");
            return getCD();
        }
        if (getCD() > 0)
            prepared = true;
        return getCD();
    }

    @Override
    public final int afterAction() {
        doAfterAction();
        if (!prepared)
            return getCD();
        prepared = false;
        int cd = 0;
        if (getMAXCD() > 0) {
            cd = getCD();
            if (cd > 0) {
                cd -= 1;
                setCD(cd);
            }
        }
        return cd;
    }

    protected void doBeforeAction() {
    }

    protected void doAfterAction() {
    }

    @Override
    public String toJSON() {
        return JSON.format("name", getName(), "CD", getCD());
    }

    protected class SealablePassiveHandler implements Sealable {
        @Override
        public boolean sealed() {
            return getSelf().passiveSealed();
        }
    }
}
