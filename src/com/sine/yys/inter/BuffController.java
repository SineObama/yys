package com.sine.yys.inter;

import com.sine.yys.buff.IBuff;
import com.sine.yys.buff.debuff.ControlBuff;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.info.IBuffProperty;
import com.sine.yys.info.Target;

import java.util.List;

/**
 * buff和debuff控制器（包括护盾），包括添加、查找等操作。
 * 会进行buff的唯一性等检查；护盾也有优先级。
 * 定义了附加buff，如龙首之玉的防御和抵抗，未来还有青坊主的抵抗。
 * 通过{@link IBuffProperty}接口返回所有buff相应属性的合计。
 */
public interface BuffController extends IBuffProperty {
    /**
     * 不同的盾不会互相排斥。
     * 相同的盾如果容量更大会进行替换。
     */
    void addShield(Shield shield);

    void removeShield(Shield shield);

    /**
     * 按照消耗顺序返回。
     */
    List<Shield> getShields();

    /**
     * 行动后执行。
     * 给效果回合数减1，减到0则移除效果。
     */
    void step(Target self);

    void addIBuff(IBuff ibuff);

    /**
     * 获取唯一的buff，不包括附加buff。
     */
    <T extends IBuff> T getUnique(Class<T> clazz);

    /**
     * @return 是否存在封印御魂buff。
     */
    boolean mitamaSealed();

    /**
     * @return 是否存在封印被动buff。
     */
    boolean passiveSealed();

    /**
     * 添加附加buff。
     */
    void addAttach(IBuff buff);

    /**
     * 获取行动控制效果，按控制优先级返回。
     */
    List<ControlBuff> getControlBuffs();

    /**
     * 移除附加buff。
     */
    <T extends IBuff> void removeAttach(Class<T> clazz);
}
