package com.sine.yys.inter;

/**
 * 描述需要进行初始化的逻辑组件，包括技能和御魂。
 */
public interface Component {
    void init(Controller controller, Entity self);
}
