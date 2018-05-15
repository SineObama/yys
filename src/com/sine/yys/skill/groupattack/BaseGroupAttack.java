package com.sine.yys.skill.groupattack;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.BaseAttackSkill;
import com.sine.yys.skill.targetresolver.EnemyCampResolver;

/**
 * 简单群攻，可多段。
 */
public abstract class BaseGroupAttack extends BaseAttackSkill {
    @Override
    public void doApply(Entity unused) {
        final Controller controller = getController();
        final Entity self = getSelf();
        for (int i = 0; i < getTimes(); i++) {
            for (Entity target : getEnemy().getAllAlive()) {
                controller.attack(self, target, getAttack(), getAttackType());
            }
        }
    }

    @Override
    public int getFire() {
        return 3;
    }

    /**
     * @return 攻击次数（段数）
     */
    public int getTimes() {
        return 1;
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyCampResolver();
    }
}
