package com.sine.yys.mitama;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 雪幽魂。
 */
public class XueYouHun extends BaseDebuffMitama {
    @Override
    public String getName() {
        return "雪幽魂";
    }

    @Override
    public double getPct() {
        return 0.12;
    }

    @Override
    public IBuff getDebuff(Entity self) {
        return new BingDong(1, self);
    }
}
