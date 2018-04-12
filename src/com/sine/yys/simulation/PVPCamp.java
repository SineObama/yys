package com.sine.yys.simulation;

import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.impl.CampInfo;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.FireRepo;
import com.sine.yys.util.Msg;

/**
 * PVP阵营。
 * <p>
 * 初始化时给式神设置唯一的鬼火仓库（自身）。
 */
public class PVPCamp extends BaseCamp implements FireRepo {
    private int fire;
    private int fireBarPos = 0;  // 鬼火行动条位置，代表点亮的格数（包括闪烁），范围0-5
    private boolean prepared = false;  // 调用ready()后为true。在2个状态之间转换
    private int increase = 3;  // 每次行动满5回合的回复鬼火数。此数字依次增长，最高5点：3 4 5 5 5...

    public PVPCamp(String name, CampInfo info, int fire) {
        super(name, info);
        this.fire = fire;
    }

    public void init(Camp enemy, Controller controller) {
        super.init(enemy, controller);
        for (ShikigamiEntityImpl shikigami : getAllShikigami()) {
            shikigami.setFireRepo(this);
        }
    }

    @Override
    public int getFire() {
        return fire;
    }

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
        log.info(Msg.info(this, "减少", count, "点鬼火，当前剩余", fire, "点"));
        return count;
    }

    @Override
    public void addFire(int count) {
        fire += count;
        if (fire > 8)
            fire = 8;
        log.info(Msg.info(this, "增加", count, "点鬼火，当前剩余", fire, "点"));
    }

    @Override
    public void ready() {
        if (prepared) {
            log.warning("异常调用ready()");
            return;
        }
        prepared = true;
        fireBarPos += 1;
        log.info(Msg.info(this, "鬼火行动条位置", fireBarPos));
    }

    @Override
    public void finish() {
        if (!prepared)
            return;
        prepared = false;
        if (fireBarPos >= 5) {
            fireBarPos = 0;
            log.info(Msg.info(this, "行动满5回合，将回复", increase, "点鬼火"));
            addFire(increase);
            if (increase < 5)
                increase += 1;
        }
    }
}
