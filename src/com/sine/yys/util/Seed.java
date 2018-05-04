package com.sine.yys.util;

import java.util.Random;

/**
 * 每次运行静态生产一个随机种子。
 * <p>
 * 种子值等同于首个{@link Random#Random()}的种子。
 */
public class Seed {
    private static final long seed = 8006678197202707420L ^ System.nanoTime();

    public static long get() {
        return seed;
    }
}
