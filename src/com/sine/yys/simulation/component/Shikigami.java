package com.sine.yys.simulation.component;

import com.sine.yys.simulation.info.Property;

import java.util.List;

/**
 * 式神。
 * 非召唤物。
 */
public abstract class Shikigami extends Entity {
    protected Shikigami(Property property, Mitama mitama, List<Skill> skills, String name) {
        super(property, mitama, skills, name);
    }
}
