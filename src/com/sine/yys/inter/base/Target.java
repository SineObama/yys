package com.sine.yys.inter.base;

/**
 * 用于统一输出信息。
 * 作为主语，可能是阵营或者式神。
 */
public interface Target extends Named {
    /**
     * @return 带阵营的全名。
     */
    String getFullName();
}
