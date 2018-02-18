package com.sine.yys.simulation.model;

import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.IProperty;
import com.sine.yys.simulation.info.Target;
import com.sine.yys.simulation.model.buff.Debuff;
import com.sine.yys.simulation.model.effect.PctEffect;
import com.sine.yys.simulation.model.skill.CommonAttack;

/**
 * 实体（包括式神和召唤物）。
 * 包含属性，buff、事件等。
 */
public interface Entity extends Target, IProperty {
    /**
     * 上层通过行动条计算得到行动的式神，调用此函数。
     */
    void action();

    double getMaxLife();

    /**
     * @return 生命百分比。
     */
    @Override
    double getLife();

    EventController getEventController();

    boolean isDead();

    BuffController getBuffController();

    FireRepo getFireRepo();

    double getPosition();

    void setPosition(double position);

    void attack(Entity target, AttackInfo attackInfo);

    void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife);

    /**
     * 概率吸取鬼火。
     */
    void randomGrab(double pct, Entity target);

    /**
     * 以效果中的概率对目标发起负面效果。
     * 包括计算效果命中和抵抗。
     * 未来将会涉及相关事件，比如抵抗反击事件、花鸟卷的被动抵抗。
     */
    void applyDebuff(PctEffect effect, Entity target, Debuff debuff);

    /**
     * 处理协战。
     * 最终依赖于{@link CommonAttack#xieZhan(Entity)}
     *
     * @param target 队友普攻的目标。
     */
    void xieZhan(Entity target);

    /**
     * 用于实现群体/多段攻击不重复计算，触发一次后会关闭BeAttackEvent事件。
     * 技能调用此函数以重置状态。
     */
    void clear();
}
