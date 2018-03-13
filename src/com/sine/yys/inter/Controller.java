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
     *
     * @param prior
     * @param action
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
     * <p>
     * 伤害逻辑：
     * 1. 由攻击、伤害系数、对方防御（忽略防御）计算。
     * 2. 根据双方buff进行增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，添加御魂效果。
     */
    void attack(Entity self0, Entity target, AttackInfo attackInfo, Collection<DebuffEffect> debuffEffects);

    /**
     * 目前只用于施加针女伤害（会被椒图或薙魂分担，不会被金鱼分担，不受一般buff和御魂影响）。
     * 1. 根据旗帜buff增减。
     * 2. 破盾。
     * 3. 施加剩余伤害，附加效果（似乎有比如山童的眩晕）。
     * <p>
     * 与一般的攻击{@link #attack(Entity, Entity, AttackInfo, Collection)}有很多相似之处，要注意同步。
     */
    void realDamage(Entity self0, Entity target, double damage);

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
}
