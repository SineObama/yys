package com.sine.yys.impl;

import com.sine.yys.info.AttackTypeEnum;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.TransferrableEffect;

import java.util.HashMap;
import java.util.Map;

public class AttackTypeImpl implements AttackType {
    boolean counter = false;
    boolean tiHun = false;
    boolean juanLiu = false;
    boolean zhenNv = false;
    boolean buff = false;

    private Map<Class, TransferrableEffect> effects = new HashMap<>();

    public AttackTypeImpl() {
    }

    public AttackTypeImpl(AttackType src, AttackTypeEnum typeEnum) {
        this.counter = src.isCounter() || typeEnum == AttackTypeEnum.COUNTER;
        this.tiHun = src.isTiHun() || typeEnum == AttackTypeEnum.TI_HUN;
        this.juanLiu = src.isJuanLiu() || typeEnum == AttackTypeEnum.JUAN_LIU;
        this.zhenNv = src.isZhenNv() || typeEnum == AttackTypeEnum.ZHEN_NV;
        this.buff = src.isBuff() || typeEnum == AttackTypeEnum.BUFF;
        for (Map.Entry<Class, TransferrableEffect> classObjectEntry : src.getEffects().entrySet()) {
            this.effects.put(classObjectEntry.getKey(), classObjectEntry.getValue().through(typeEnum));
        }
    }

    public static AttackType createCounter() {
        AttackTypeImpl attackType = new AttackTypeImpl();
        attackType.counter = true;
        return attackType;
    }

    public static AttackType createBuff() {
        AttackTypeImpl attackType = new AttackTypeImpl();
        attackType.buff = true;
        return attackType;
    }

    @Override
    public <T> T getEffect(Class<T> tClass) {
        return (T) effects.get(tClass);
    }

    @Override
    public Map<Class, TransferrableEffect> getEffects() {
        return effects;
    }

    @Override
    public boolean isCounter() {
        return counter;
    }

    @Override
    public boolean isTiHun() {
        return tiHun;
    }

    @Override
    public boolean isJuanLiu() {
        return juanLiu;
    }

    @Override
    public boolean isZhenNv() {
        return zhenNv;
    }

    @Override
    public boolean isBuff() {
        return buff;
    }
}
