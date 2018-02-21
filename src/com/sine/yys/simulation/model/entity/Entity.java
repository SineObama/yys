package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.BuffController;
import com.sine.yys.simulation.component.event.EventController;
import com.sine.yys.simulation.component.operation.OperationHandler;
import com.sine.yys.simulation.model.AttackInfo;
import com.sine.yys.simulation.model.IProperty;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.FireRepo;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.buff.Debuff;
import com.sine.yys.simulation.model.effect.PctEffect;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.CommonAttack;

import java.util.List;

/**
 * 实体（包括式神和召唤物）。
 * 包含属性，buff、事件等。
 */
public interface Entity extends Target, IProperty {
    List<ActiveSkill> getActiveSkills();

    void init(InitContext context);

    /**
     * 上层通过行动条计算得到行动的式神，调用此函数。
     */
    void action();

    int getMaxLife();

    /**
     * @return 生命百分比。
     */
    @Override
    double getLife();

    void setLife(int life);

    OperationHandler getAI();

    EventController getEventController();

    boolean isDead();

    Camp getCamp();

    BuffController getBuffController();

    FireRepo getFireRepo();

    double getPosition();

    void setPosition(double position);

    void attack(Entity target, AttackInfo attackInfo);

    void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife);

    int getLifeInt();

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
     * 最终依赖于{@link CommonAttack#xieZhan(Entity, Entity)}
     *
     * @param target 队友普攻的目标。
     */
    void xieZhan(Entity target);

    /**
     * 用于实现群体/多段攻击不重复计算，触发一次后会关闭BeAttackEvent事件。
     * 技能调用此函数以重置状态。
     */
    void clear();

    /**
     * 添加护盾时计算护盾的数值（厚度）。计算暴击。
     *
     * @param src 原本数值。
     * @return 最终数值。
     */
    int shieldValue(double src);
}
