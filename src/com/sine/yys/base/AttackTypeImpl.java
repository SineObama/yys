package com.sine.yys.base;

import com.sine.yys.inter.AttackType;

public class AttackTypeImpl implements AttackType {
    private boolean counter = false;
    private boolean tiHun = false;
    private boolean juanLiu = false;
    private boolean zhenNv = false;
    private boolean buff = false;

    public AttackTypeImpl(boolean counter, boolean tiHun, boolean juanLiu, boolean zhenNv, boolean buff) {
        this.counter = counter;
        this.tiHun = tiHun;
        this.juanLiu = juanLiu;
        this.zhenNv = zhenNv;
        this.buff = buff;
    }

    public AttackTypeImpl(AttackType type) {
        this.counter = type.isCounter();
        this.tiHun = type.isTiHun();
        this.juanLiu = type.isJuanLiu();
        this.zhenNv = type.isZhenNv();
        this.buff = type.isBuff();
    }

    public AttackTypeImpl(boolean counter) {
        this.counter = counter;
    }

    public AttackTypeImpl() {
    }

    @Override
    public boolean isCounter() {
        return counter;
    }

    @Override
    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    @Override
    public boolean isTiHun() {
        return tiHun;
    }

    @Override
    public void setTiHun(boolean tiHun) {
        this.tiHun = tiHun;
    }

    @Override
    public boolean isJuanLiu() {
        return juanLiu;
    }

    @Override
    public void setJuanLiu(boolean juanLiu) {
        this.juanLiu = juanLiu;
    }

    @Override
    public boolean isZhenNv() {
        return zhenNv;
    }

    @Override
    public void setZhenNv(boolean zhenNv) {
        this.zhenNv = zhenNv;
    }

    @Override
    public boolean isBuff() {
        return buff;
    }

    @Override
    public void setBuff(boolean buff) {
        this.buff = buff;
    }
}
