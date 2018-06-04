package com.sine.yys.impl;

import com.sine.yys.info.TransferType;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.TransferrableEffect;

import java.util.HashMap;
import java.util.Map;

public class AttackTypeImpl implements AttackType {
    boolean counter = false;
    boolean tiHun = false;
    boolean juanLiu = false;
    boolean zhenNv = false;
    boolean caoRen = false;
    boolean buff = false;
    boolean wake = true;
    boolean trigger = true;  // 触发御魂和被动效果。包括一矢·封魔和针线等

    private Map<Class, TransferrableEffect> effects = new HashMap<>();

    public AttackTypeImpl() {
    }

    /**
     * 根据某一种伤害变换，创建新的实例。
     */
    public AttackTypeImpl(AttackType src, TransferType typeEnum) {
        this.counter = src.isCounter();
        this.tiHun = src.isTiHun() || typeEnum == TransferType.TI_HUN;
        this.juanLiu = src.isJuanLiu() || typeEnum == TransferType.JUAN_LIU;
        this.zhenNv = src.isZhenNv() || typeEnum == TransferType.ZHEN_NV;
        this.caoRen = src.isCaoRen() || typeEnum == TransferType.CAO_REN;
        this.buff = src.isBuff();
        for (Map.Entry<Class, TransferrableEffect> classObjectEntry : src.getEffects().entrySet()) {
            TransferrableEffect value = classObjectEntry.getValue();
            if (value != null)
                this.effects.put(classObjectEntry.getKey(), value.through(typeEnum));
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
    public boolean isCaoRen() {
        return caoRen;
    }

    @Override
    public boolean isBuff() {
        return buff;
    }

    @Override
    public boolean isWake() {
        return wake;
    }

    @Override
    public boolean isTrigger() {
        return trigger;
    }
}
