package com.sine.yys.impl;

import com.sine.yys.info.TransferType;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TransferrableEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * 基类。攻击发生传递时通过构造函数构建新对象。
 * <p>
 * 非暴击。一般描述分摊后（非本体）受到的攻击类型，和针女、草人等类型。
 */
public class AttackTypeImpl implements AttackType {
    boolean counter = false;
    boolean tiHun = false;
    boolean juanLiu = false;
    boolean zhenNv = false;
    boolean caoRen = false;
    boolean buff = false;
    boolean wake = true;
    boolean trigger = true;  // 触发御魂和被动效果。包括一矢·封魔和针线等
    boolean indirect = false;
    Double damage;

    private List<TransferrableEffect> effects = new ArrayList<>();

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
        this.wake = src.isWake();
        this.trigger = src.isTrigger();
        this.damage = src.getDamage();
        for (TransferrableEffect effect : src.getEffects()) {
            effect = effect.through(typeEnum);
            if (effect != null)
                this.effects.add(effect);
        }
    }

    @Override
    public List<TransferrableEffect> getEffects() {
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
        return !buff && trigger;
    }

    @Override
    public boolean isIndirect() {
        return indirect;
    }

    @Override
    public double getDamage() {
        if (damage == null)
            return 0.0;
        return damage;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public boolean isCritical() {
        return false;
    }

    @Override
    public void handle(Entity self, Entity target, AttackType attackType, List<DebuffEffect> debuffEffects) {
        for (TransferrableEffect transferrableEffect : effects) {
            transferrableEffect.handle(self, target, attackType, debuffEffects);
        }
    }

    @Override
    public boolean isOrigin() {
        return !tiHun && !juanLiu && !buff && !indirect;
    }

    @Override
    public boolean isSharable(){
        return !buff && !indirect;
    }
}
