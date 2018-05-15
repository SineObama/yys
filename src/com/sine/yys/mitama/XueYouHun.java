package com.sine.yys.mitama;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 雪幽魂。
 */
public class XueYouHun extends BaseDebuffMitama {
    private boolean forbidden = false; // 用于雪童子冰冻或破冰时禁用雪幽魂

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

    @Override
    public boolean sealed() {
        return super.sealed() || forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }
}
