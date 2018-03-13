package com.sine.yys.event;

/**
 * 阵营或式神受击事件（阵营和式神都有此事件），包括反击。
 * 目前仅为实现辉夜姬幻境下的被动，群体、多段攻击不重复计算：
 * 1. 在敌方回合最多触发一次；
 * 2. 被敌方反击触发一次（每个狰反击分开算）；
 * 3. 被彼岸花被动攻击触发一次；
 * 4. 受中毒伤不触发。
 */
public class BeAttackEvent extends BaseEvent implements Event {
}
