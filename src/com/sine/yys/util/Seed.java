package com.sine.yys.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 每次运行静态生产一个随机种子。
 * <p>
 * 种子生产方法来自{@link Random#Random()}。
 */
public class Seed {
    private static final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);
    private static final long seed = seedUniquifier() ^ System.nanoTime();

    private static long seedUniquifier() {
        // L'Ecuyer, "Tables of Linear Congruential Generators of
        // Different Sizes and Good Lattice Structure", 1999
        for (; ; ) {
            long current = seedUniquifier.get();
            long next = current * 181783497276652981L;
            if (seedUniquifier.compareAndSet(current, next))
                return next;
        }
    }

    public static long get() {
        return seed;
    }
}
