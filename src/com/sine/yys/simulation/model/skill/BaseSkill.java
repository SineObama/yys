package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.ActionContext;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.logging.Logger;

public abstract class BaseSkill implements Skill {
    protected final Logger log = Logger.getLogger(this.getClass().toString());
    private final int MAXCD;
    private int CD = 0;

    public BaseSkill(int MAXCD) {
        this.MAXCD = MAXCD;
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
     * 扣除真实生命，并检查死亡。
     * 以后要区分召唤物和式神的情况
     */
    protected void applyRealDamage(ActionContext context, Entity target, int damage) {
        log.info(Msg.damage(context, target, damage));
        if (target.getLife() > damage) {
            target.setLife(target.getLife() - damage);
        } else {
            target.setLife(0);
            context.getEnemy().getPosition(target).setDead(true);
        }
    }

    /**
     * 随机吸取鬼火。
     * 目前只有青行灯用。
     */
    protected void randomGrab(ActionContext context, double pct, int times) {
        final int num = RandUtil.count(pct, times);
        if (num > 0) {
            Camp own = context.getOwn(), enemy = context.getEnemy();
            own.addFire(enemy.grabFire(num));
        }
    }
}
