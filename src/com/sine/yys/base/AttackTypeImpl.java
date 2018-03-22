package com.sine.yys.base;

import com.sine.yys.inter.AttackType;

public class AttackTypeImpl implements AttackType {
    private boolean counter, tiHun, juanLiu, zhenNv;

    public AttackTypeImpl(boolean counter, boolean tiHun, boolean juanLiu, boolean zhenNv) {
        this.counter = counter;
        this.tiHun = tiHun;
        this.juanLiu = juanLiu;
        this.zhenNv = zhenNv;
    }

    public AttackTypeImpl(AttackType type) {
        this.counter = type.isCounter();
        this.tiHun = type.isTiHun();
        this.juanLiu = type.isJuanLiu();
        this.zhenNv = type.isZhenNv();
    }

    public AttackTypeImpl(boolean counter) {
        this.counter = counter;
        this.tiHun = false;
        this.juanLiu = false;
        this.zhenNv = false;
    }

    public AttackTypeImpl() {
        this.counter = false;
        this.tiHun = false;
        this.juanLiu = false;
        this.zhenNv = false;
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
}
