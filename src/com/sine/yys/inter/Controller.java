package com.sine.yys.inter;

import com.sine.yys.buff.debuff.Debuff;
import com.sine.yys.info.AttackInfo;
import com.sine.yys.info.PctEffect;

/**
 * 控制器，提供给技能和御魂实现操作。
 * 包含了战斗的重要逻辑。
 * 附带当前所属式神、阵营的信息等。
 * 比如进行攻击、附加效果等。
 */
public interface Controller {
    /**
     * 获取式神所在的阵营。
     * <p>
     * 由于从阵营可以获取式神，为了避免双向引用，强行写成这样。
     */
    Camp getCamp(Entity entity);

    void attack(Entity self0, Entity target, AttackInfo attackInfo);

    void realDamage(Entity self0, Entity target, double maxByAttack, double maxPctByMaxLife);

    void randomGrab(Entity self, Entity target, double pct);

    void applyDebuff(Entity self, PctEffect effect, Entity target, Debuff debuff);

    void xieZhan(Entity self, Entity target);

    void clear();
}
