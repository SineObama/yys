package com.sine.yys.util;

import java.util.Random;

/**
 * 线程独立的随机种子，由{@linkplain Seed}衍生出。
 * <p>
 * 第一个种子使用{@linkplain Seed}，是为了让使用结果和支持多线程前相同。
 */
public class ThreadLocalSeed extends ThreadLocal<Long> {
    private static final ThreadLocal<Long> seed = new ThreadLocalSeed();
    private static Random seedGenerator = null;

    public static synchronized void reset() {
        seedGenerator = null;
    }

    public static long getSeed() {
        return seed.get();
    }

    @Override
    protected synchronized Long initialValue() {
        if (seedGenerator != null)
            return seedGenerator.nextLong();
        seedGenerator = new Random(Seed.get());
        return Seed.get();
    }
}
