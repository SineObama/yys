package com.sine.yys.inter;

import com.sine.yys.info.PctEffect;

public interface DebuffEffect extends PctEffect {
    /**
     * 是否受效果命中/抵抗影响。
     *
     * @return 是否受影响。
     */
    boolean involveHitAndDef();

    Debuff getDebuff();
}
