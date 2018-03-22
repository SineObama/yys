package com.sine.yys.inter;

/**
 * 需要进行初始化的逻辑组件，包括技能和御魂。
 */
public interface Component {
    /**
     * @param self 所属式神。
     * @param own  所在阵营。
     */
    void init(Controller controller, Self self, Camp own);
}
