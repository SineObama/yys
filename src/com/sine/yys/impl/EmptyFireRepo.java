package com.sine.yys.impl;

import com.sine.yys.inter.FireRepo;

public class EmptyFireRepo implements FireRepo {
    @Override
    public int getFire() {
        return 0;
    }

    @Override
    public void useFire(int count) {
        throw new RuntimeException("EmptyFireRepo can't use fire");
    }

    @Override
    public int grabFire(int count) {
        return 0;
    }

    @Override
    public void addFire(int count) {
    }

    @Override
    public void ready(boolean newRound) {
    }

    @Override
    public void finish(boolean newRound) {
    }
}
