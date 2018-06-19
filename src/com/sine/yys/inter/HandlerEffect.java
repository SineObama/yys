package com.sine.yys.inter;

import java.util.List;

/**
 * 在一次攻击中进行一定的处理。
 * <p>
 * 目前用于实现霜天之织效果的传递性。
 */
public interface HandlerEffect {
    void handle(Entity self, Entity target, AttackType attackType, List<DebuffEffect> debuffEffects);
}
