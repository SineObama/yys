package com.sine.yys.simulation.component.model;

/**
 * 鬼火仓库，管理鬼火。
 * 定义：在行动前调用step()以增加鬼火（鬼火条推进一格）。
 * <p>在PVE中，敌方式神的鬼火是独立的，有自己的仓库、管理方法，会随式神回合开始时回复1火。
 * 再参考木魅御魂效果“减少目标鬼火”，是针对式神发生的动作。
 * 故抽象出此概念，只不过在PVP中，每个式神都是引用己方唯一的仓库，而且是每5回合回火。</p>
 */
public interface FireRepo {
    /**
     * 获取鬼火数。
     */
    int getFire();

    /**
     * 使用（减去）鬼火数。不得使用超过已有鬼火的数量。
     */
    void useFire(int count);

    /**
     * 抢夺（吸取）鬼火。
     *
     * @return 成功数（可能鬼火不足）
     */
    int grabFire(int count);

    /**
     * 增加鬼火。
     */
    void addFire(int count);

    /**
     * 行动前调用。
     * 对于独立的仓库，回复1火；对于PVP阵营，鬼火行动条前进一格。
     */
    void step();
}
