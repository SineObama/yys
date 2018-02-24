package com.sine.yys.inter;

import com.sine.yys.info.Named;

/**
 * 技能。
 * 只有逻辑，状态保存在{@link Entity}中。
 * 获取CD等，提供step函数。
 */
public interface Skill extends Named {
    void init(Controller controller, Entity self);

    int getCD(Entity self);

    int getMAXCD();

    /**
     * 每回合开始调用。
     *
     * @param controller 控制器。
     * @param self       式神自身。
     */
    void beforeAction(Controller controller, Entity self);

    /**
     * 每回合结束调用。
     *
     * @param controller 控制器。
     * @param self       式神自身。
     */
    int afterAction(Controller controller, Entity self);
}
