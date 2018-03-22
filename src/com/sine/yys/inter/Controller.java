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
     *
     * @param prior 优先级。值小的优先级高，先执行。
     */
    void addAction(int prior, Callback action);

    /**
     * 主动攻击目标（包括赤团华，不包括持续伤害和反击）。
     */
    void attack(Entity self, Entity target, AttackInfo attackInfo);

    /**
     * 反击。
     */
    void counter(Entity self, Entity target, AttackInfo attackInfo);

    /**
     * 针女伤害（会被椒图或薙魂分担，不会被金鱼分担，不受一般buff影响）。
     */
    void realDamage(Entity self, Entity target, double damage, AttackType type);

    /**
     * 薙魂伤害、椒图分摊后（其他人的）伤害。
     * 会打醒睡眠。
     */
    void directDamage(Entity src, Entity self, int damage, AttackType type);

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

    /**
     * 获得一次行动机会
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
