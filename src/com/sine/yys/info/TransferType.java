package com.sine.yys.info;

/**
 * 转换类型。
 * <p>
 * 在攻击触发的针女、薙魂等时候，此类型作为信息，让{@linkplain com.sine.yys.inter.AttackType 攻击类型}进行更新。
 * <p>
 * 比如反击触发的针女是反击针女；反击触发了的薙魂、涓流，受伤者受到的攻击也是反击类型的。
 */
public enum TransferType {
    ZHEN_NV, TI_HUN, JUAN_LIU, CAO_REN
}
