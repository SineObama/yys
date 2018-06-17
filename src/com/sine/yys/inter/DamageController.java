package com.sine.yys.inter;

/**
 * 伤害控制器。
 * <p>
 * {@linkplain IBuff buff}实现伤害和治疗的接口。
 */
public interface DamageController {
    /**
     * 持续伤害（毒伤）。
     * 直接造成伤害（不计算暴击），不再计算任何buff加成效果，可被护盾减免，不受被分摊。
     * <p>
     * 用于实现不会暴击的持续伤害效果。
     */
    void buffDamage(Entity self, Entity target, AttackType buff);

    /**
     * 治疗，包含暴击计算。
     *
     * @param src 初始治疗量。
     * @return 实际治疗量（受减疗影响，不算奶满的影响）。
     */
    int cure(Entity self, Entity target, double src);

    /**
     * 计算暴击。
     * 包括添加护盾或治疗的时候。
     *
     * @param src 原本数值。
     * @return 最终数值。
     */
    int calcCritical(Entity self, double src);
}
