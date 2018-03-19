package com.sine.yys.inter;

import java.util.Collection;

/**
 * 控制器，提供给技能和御魂实现操作。
 * 包含了战斗的重要逻辑。
 * 比如进行攻击、附加效果等。
 */
public interface Controller extends DamageController {
    /**
     * 添加一个动作
     */
    void addAction(int prior, CallBack action);

    Camp getCamp0();

    Camp getCamp1();

    /**
     * 获取式神所在的阵营。
     * <p>
     * 由于从阵营可以获取式神，为了避免双向引用，强行写成这样。
     */
    Camp getCamp(Entity entity);

    /**
     * 攻击目标（主动攻击，包括彼岸花的被动，不包括持续伤害和反击），可暴击，可被护盾减免。
     * 受一般buff、旗帜buff影响。
     * 触发附加攻击时的效果、造成伤害时的效果、击杀时的效果等。
     * 也触发目标被攻击的事件（导致反击等）。
     */
    void attack(Entity self0, Entity target, AttackInfo attackInfo, Collection<DebuffEffect> debuffEffects);

    /**
     * 目前只用于施加针女伤害（会被椒图或薙魂分担，不会被金鱼分担，不受一般buff影响）。
     */
    void realDamage(Entity self0, Entity target, double damage);

    void directDamage(Entity src, Entity self0, int damage);

    /**
     * 概率吸取鬼火。
     */
    void randomGrab(Entity self, Entity target, double pct);

    /**
     * 以效果中的概率对目标发起负面效果。
     * 包括计算效果命中和抵抗。
     * 未来将会涉及相关事件，比如抵抗反击事件、花鸟卷的被动抵抗。
     */
    void applyDebuff(Entity self, Entity target, DebuffEffect effect);

    /**
     * 处理协战（可能目标已死亡，或者自己被嘲讽）。
     *
     * @param target 队友普攻的目标。
     */
    void xieZhan(Entity self, Entity target);

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
