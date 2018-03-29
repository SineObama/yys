package com.sine.yys.inter;

import com.sine.yys.buff.debuff.Debuff;
import com.sine.yys.inter.base.Named;

/**
 * 定义一个可以附加{@linkplain Debuff}的效果。
 */
public interface DebuffEffect extends Named, PctEffect {
    /**
     * 是否受效果命中/抵抗影响。
     *
     * @return 是否受影响。
     */
    boolean involveHitAndDef();

    /**
     * 获取附加的debuff。
     *
     * @param self 施加的式神。
     */
    IBuff getDebuff(Entity self);
}
