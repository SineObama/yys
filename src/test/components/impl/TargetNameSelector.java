package test.components.impl;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.base.Named;
import test.components.inter.TargetSelector;

import java.util.List;
import java.util.logging.Logger;

/**
 * 根据目标名字进行选择。
 */
public class TargetNameSelector implements TargetSelector {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private Named entity;
    private String name;

    public TargetNameSelector(Named entity) {
        this.entity = entity;
    }

    public TargetNameSelector(String name) {
        this.name = name;
    }

    public void setName(Named entity) {
        this.name = entity.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Entity select(Entity self, Camp own, ActiveSkill skill, List<? extends Entity> entities) {
        if (name == null)
            name = entity.getName();
        if (entities.isEmpty())
            return null;
        for (Entity entity : entities) {
            if (entity.getName().equals(name))
                return entity;
        }
        log.severe(name + " entity not found");
        return null;
    }
}
