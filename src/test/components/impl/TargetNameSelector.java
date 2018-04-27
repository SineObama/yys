package test.components.impl;

import com.sine.yys.inter.Entity;
import test.components.inter.TargetSelector;

import java.util.List;
import java.util.logging.Logger;

public class TargetNameSelector implements TargetSelector {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private String name;

    public TargetNameSelector(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Entity select(List<? extends Entity> entities) {
        for (Entity entity : entities) {
            if (entity.getName().equals(name))
                return entity;
        }
        log.severe(name + " entity not found");
        return null;
    }
}
