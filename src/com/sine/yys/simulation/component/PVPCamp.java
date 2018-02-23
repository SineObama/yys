package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.Camp;
import com.sine.yys.simulation.component.model.FireRepo;
import com.sine.yys.simulation.util.Msg;

/**
 * PVP阵营。
 * 初始化时给式神设置唯一的鬼火仓库（自己）。
 */
public class PVPCamp extends BaseCamp implements Camp, FireRepo {
    private int fire;
    private int fireBarPos = 0;
    private int increase = 3;

    public PVPCamp(String name, int fire) {
        super(name);
        this.fire = fire;
    }

    public void init(Camp enemy) {
        super.init(enemy);
        for (Shikigami shikigami : getAllShikigami2()) {
            shikigami.setFireRepo(this);
        }
    }

    @Override
    public int getFire() {
        return fire;
    }

    @Override
    public int getIncrease() {
        return increase;
    }

    @Override
    public void useFire(int count) {
        fire -= count;
    }

    @Override
    public int grabFire(int count) {
        if (count > fire)
            count = fire;
        fire -= count;
        return count;
    }

    @Override
    public void addFire(int count) {
        fire += count;
        if (fire > 8)
            fire = 8;
        log.info(Msg.info(this, "增加 " + count + " 点鬼火，当前剩余 " + fire + " 点"));
    }

    @Override
    public void step() {
        fireBarPos += 1;
        if (fireBarPos > 5) {
            fireBarPos = 1;
            log.info(Msg.info(this, "行动满5回合，将回复 " + increase + " 点鬼火"));
            addFire(increase);
            if (increase < 5)
                increase += 1;
        }
    }
}
