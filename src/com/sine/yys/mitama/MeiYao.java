package com.sine.yys.mitama;

import com.sine.yys.buff.control.HunLuan;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

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
    public IBuff getDebuff(Entity self) {
        return new HunLuan(self);
    }
}
