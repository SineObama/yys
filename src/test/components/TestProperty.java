package test.components;

import com.sine.yys.inter.base.Property;

/**
 * 测试用的基础属性实现，可随时修改数值。提供一定默认值。
 * 默认无防御暴击等属性。
 */
public class TestProperty implements Property {
    public double attack = 1000.0;
    public double life = 10000.0;
    public double defense = 0.0;
    public double speed = 100.0;
    public double critical = 0.0;
    public double criticalDamage = 2.0;
    public double effectHit = 0.0;
    public double effectDef = 0.0;

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public double getLife() {
        return life;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getCritical() {
        return critical;
    }

    @Override
    public double getCriticalDamage() {
        return criticalDamage;
    }

    @Override
    public double getEffectHit() {
        return effectHit;
    }

    @Override
    public double getEffectDef() {
        return effectDef;
    }
}
