package test.components.inter;

import com.sine.yys.inter.Entity;

import java.util.List;

@FunctionalInterface
public interface TargetSelector {
    Entity select(List<? extends Entity> entities);
}
