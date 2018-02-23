package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.event.EventController;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.PctEffect;

/**
 * 控制器，技能和御魂实现操作所调用的接口。
 * 附带当前所属式神、阵营的信息等。
 * 比如进行攻击、附加效果等。
 */
public interface Controller {
    Entity getSelf();

    Camp getOwn();

    Camp getCamp(Entity entity);

    Camp getEnemy();

    /**
     * 所属式神的{@link EventController}事件控制器。
     */
    EventController getEventController();

    void attack(Entity target, AttackInfo attackInfo);

    void realDamage(Entity target, double maxByAttack, double maxPctByMaxLife);

    void randomGrab(double pct, Entity target);

    void applyDebuff(PctEffect effect, Entity target, Debuff debuff);

    void xieZhan(Entity target);

    void clear();
}
