package com.sine.yys.simulation.component.inter;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.FireRepo;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.effect.PctEffect;
import com.sine.yys.simulation.component.model.BuffController;
import com.sine.yys.simulation.component.model.EventController;
import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.component.skill.ActiveSkill;
import com.sine.yys.simulation.component.skill.Skill;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.IProperty;
import com.sine.yys.simulation.info.Target;

import java.util.List;

public interface MitamaController extends Target, IProperty {
    abstract <T extends Skill, V> V get(Class<T> clazz, Object key, V defaultValue);

    abstract <T extends Skill> void put(Class<T> clazz, Object key, Object value);

    abstract int getMaxLife();


    abstract EventController getEventController();

    abstract List<ActiveSkill> getActiveSkills();

    abstract int shieldValue(double src);

    abstract void init(InitContext context);

    abstract boolean isDead();

    abstract Camp getCamp();

    abstract BuffController getBuffController();

    abstract FireRepo getFireRepo();

    abstract double getPosition();

    abstract void setPosition(double position);

    abstract void attack(Entity target, AttackInfo attackInfo);

    abstract void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife);

    abstract void randomGrab(double pct, Entity target);

    abstract void applyDebuff(PctEffect effect, Entity target, Debuff debuff);

    abstract void xieZhan(Entity target);

    abstract void clear();
}
