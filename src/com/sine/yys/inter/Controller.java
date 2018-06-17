package com.sine.yys.inter;

import com.sine.yys.inter.base.Callback;

/**
 * 控制器。
 * <p>
 * 技能和御魂实现操作的接口。
 * 包含了战斗的重要逻辑。
 * 比如如何进行攻击、附加效果等。
 */
public interface Controller extends DamageController {
    /**
     * 添加一个动作。将在当前回合（动作）结束后执行。
     * <p>
     * 用于实现反击等。
     *
     * @param prior 优先级。值小的优先级高，先执行。
     */
    void addAction(int prior, Callback action);

    /**
     * 直接攻击目标。可能被分摊。
     *
     * 实现内容：正常攻击、反击、赤团华、针女。不包括持续伤害。
     * 具体伤害由 AttackType 给出。
     */
    void attack(Entity self, Entity target, AttackType attackType);

    /**
     * 直接的伤害输出。可能被分摊。
     *
     * 实现内容：薙魂伤害、涓流分摊后（其他人的）伤害、草人伤害。
     */
    void directDamage(Entity src, Entity self, AttackType type);

    /**
     * 治疗。（不会计算暴击）
     *
     * @param src 初始治疗量。
     * @return 实际治疗量（受减疗影响，不算奶满的影响）。
     */
    int cureWithoutCritical(Entity self, Entity target, double src);

    /**
     * 概率吸取鬼火。
     */
    void randomGrab(Entity self, Entity target, double pct);

    /**
     * 以指定概率对目标发起负面效果。
     * <p>
     * 包含效果命中和抵抗的计算。
     * 涉及抵抗反击事件、画境抵消效果等。
     */
    void applyDebuff(Entity self, Entity target, DebuffEffect effect);

    void afterMovement();

    /**
     * 获得一次行动机会。
     */
    void actionChance(Entity self);

    /**
     * 复活目标，并设置生命为指定值。
     */
    void revive(Entity target, int maxLife);

    /**
     * 驱散增益效果。
     */
    int dispelBuff(Entity target, int count);

    /**
     * 驱散减益效果。
     */
    int dispelDebuff(Entity target, int count);
}
