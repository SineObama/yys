package com.sine.yys.inter;

import com.sine.yys.inter.base.Named;

/**
 * 御魂。
 * <p>
 * 在一定条件下产生效果，提升式神、阵营的实力。
 * 一般每个式神{@linkplain com.sine.yys.inter.Entity 实体}只能绑定一种御魂效果。
 * <p>
 * 御魂效果多数是在特定情况（事件）下触发，通过监听事件实现。
 */
public interface Mitama extends Named {
}
