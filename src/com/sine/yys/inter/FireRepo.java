package com.sine.yys.inter;

/**
 * 鬼火仓库。用于管理鬼火。
 * <p>
 * PVP行动条的行为定义：行动前调用{@link #ready(boolean)}使行动条预备推进一格（UI上显示为增加一格闪烁）；行动结束后调用{@link #finish(boolean)}完成当前格，在完成5格时结算鬼火。
 * 必须依次调用这2个函数，多次调用同一函数无效果。
 * <p>
 * 创建原因：
 * 在PVE中，敌方式神的鬼火是独立的，有自己的仓库、管理方法，会随式神回合开始时回复1火。
 * 再参考木魅御魂效果“减少目标鬼火”，是针对式神发生的动作。
 * 故抽象出此概念，只不过在PVP中，每个式神都是引用己方唯一的仓库，而且是每5回合回火。
 * <p>
 * 由原本的单一函数step改为前后分别调用的2个函数，契机是镰鼬的被动“人多势众”，同时也为了更符合UI的表现（虽然也只是UI）。
 * 于是镰鼬被动（或轮入道）的实现为，首次行动正常调用2个函数，之后的行动不调用{@link #ready(boolean)}，与UI一样没有预备推进的表现，多次调用{@link #finish(boolean)}也不会有效果。
 */
public interface FireRepo {
    /**
     * @return 当前鬼火数。
     */
    int getFire();

    /**
     * @param count 使用（减去）鬼火数。不得使用超过已有鬼火的数量。
     */
    void useFire(int count);

    /**
     * @param count 抢夺（吸取）鬼火数。
     * @return 成功数（可能鬼火不足）
     */
    int grabFire(int count);

    /**
     * @param count 增加鬼火数。
     */
    void addFire(int count);

    /**
     * 行动前调用。
     * <p>
     * 对于PVE独立的仓库，立即回复1火。
     * <p>
     * 对于PVP阵营，鬼火行动条预备推进一格（UI上显示为增加一格闪烁）。
     *
     * @param newRound 是否额外获得的回合。
     */
    void ready(boolean newRound);

    /**
     * 行动后调用。
     * <p>
     * 对于PVE独立的仓库，无效果。
     * <p>
     * 对于PVP阵营，鬼火行动条完成当前格，并在完成5格时结算鬼火。
     *
     * @param newRound 是否额外获得的回合。
     */
    void finish(boolean newRound);
}
