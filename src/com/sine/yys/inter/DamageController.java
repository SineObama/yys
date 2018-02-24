package com.sine.yys.inter;

/**
 * 伤害控制器，提供给buff实现伤害。
 */
public interface DamageController {
    /**
     * 直接造成伤害，不再计算任何buff加成效果，可被护盾减免，不受椒图分摊。
     * <p>
     * 用于实现持续伤害效果。
     * 未来也可实现椒图分摊后的伤害。
     *
     * @param damage 伤害值。
     */
    void directDamage(Entity self, int damage);
}
