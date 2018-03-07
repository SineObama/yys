package com.sine.yys.inter;

/**
 * 伤害控制器，单纯提供给buff实现伤害和治疗。
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

    /**
     * 治疗。
     *
     * @param target 目标。
     * @param src    原治疗量（包括暴击）。
     * @return 实际治疗量（受减疗影响。不算奶满的影响）。
     */
    int cure(Entity target, double src);

    /**
     * @param self   自身
     * @param target 治疗目标。
     * @param pct    自身生命百分比。
     * @return
     */
    int cureByLifePct(Entity self, Entity target, double pct);

    /**
     * 计算暴击。
     * 包括添加护盾或治疗的时候。
     *
     * @param src 原本数值。
     * @return 最终数值。
     */
    int calcCritical(Entity self, double src);
}
