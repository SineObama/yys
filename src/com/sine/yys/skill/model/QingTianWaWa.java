package com.sine.yys.skill.model;

import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

import java.util.logging.Logger;

/**
 * 日和坊-晴天娃娃。
 */
public class QingTianWaWa {
    private static final String ENERGY = "日光能量";
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private final int MAXCD;
    private final Entity self;
    private int cd = 0;
    private int energy = 0;

    public QingTianWaWa(int maxcd, Entity self) {
        MAXCD = maxcd;
        this.self = self;
    }

    public int getCd() {
        return cd;
    }

    public int step() {
        if (cd > 0)
            cd -= 1;
        return cd;
    }

    public void add(int count) {
        int added = energy + count;
        if (added > self.getMaxLife()) {
            added = self.getMaxLife();
            log.info(Msg.info(self, ENERGY, "达到上限"));
        }
        log.info(Msg.info(self, "本次增加", ENERGY, (added - energy)));
        energy = added;
        return;
    }

    /**
     * 使用指定数量的能量，可超过当前值。
     *
     * @return 实际使用的能量。
     */
    public int use(int count) {
        if (count > energy)
            count = energy;
        energy -= count;
        log.info(Msg.info(self, "使用", ENERGY, count, "剩余", energy));
        return count;
    }

    public void sacrifice() {
        cd = MAXCD;
    }

    public int getEnergy() {
        return energy;
    }
}
