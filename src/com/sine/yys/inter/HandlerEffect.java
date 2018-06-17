package com.sine.yys.inter;

import java.util.List;

public interface HandlerEffect {
    void handle(Entity self, Entity target, AttackType attackType, List<DebuffEffect> debuffEffects);
}
