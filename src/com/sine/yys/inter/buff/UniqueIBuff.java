package com.sine.yys.inter.buff;

/**
 * 只能存在一个的buff。
 * 替换时只检查持续回合数，长的可以替换短的。
 * 目前只有控制类debuff。
 * TODO 考虑设置判断是否替换buff的接口，如compareTo。
 */
public interface UniqueIBuff extends IBuff {
}
