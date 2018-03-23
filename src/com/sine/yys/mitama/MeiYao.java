package com.sine.yys.mitama;

import com.sine.yys.buff.debuff.control.HunLuan;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.Entity;

/**
 * 魅妖。
 */
public class MeiYao extends BaseDebuffMitama {
    @Override
    public String getName() {
        return "魅妖";
    }

    @Override
    public double getPct() {
        return 0.25;
    }

    @Override
    public Debuff getDebuff(Entity self) {
        return new HunLuan(self);
    }
}
