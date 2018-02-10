package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.component.event.EventController;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.entity.Shikigami;

import java.util.List;

/**
 * 阵营。
 */
public interface Camp extends Target {
    String getName();

    void addEntity(Entity entity);

    List<Entity> getAllAlive();

    List<Shikigami> getAllShikigami();

    boolean contain(Entity entity);

    /**
     * 获取鬼火数
     */
    int getFire();

    int getIncrease();

    /**
     * 使用（减去）鬼火数
     */
    void useFire(int count);

    /**
     * 抢夺（吸取）鬼火。
     *
     * @return 成功数（可能因为鬼火不足）
     */
    int grabFire(int count);

    /**
     * 增加鬼火数。
     */
    void addFire(int count);

    /**
     * 鬼火行动条前进一格。
     * 暂定为行动前进行。
     *
     * @return 行动条进度，表示灯亮的数目，1~5。
     */
    int step();

    Position getPosition(Entity entity);

    EventController getEventController();

    void init(InitContext context);

    Entity randomTarget();
}
