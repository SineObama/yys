package com.sine.yys.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 随机实用类。
 * <p>
 * 提供线程独立的随机方法。
 */
public class RandUtil {
    private static final Logger log = Logger.getLogger(RandUtil.class.getName());
    private static final ThreadLocal<Random> random = ThreadLocal.withInitial(() -> {
        long seed = ThreadLocalSeed.getSeed();
        log.info("using random seed: " + seed + "L");
        return new Random(seed);
    });

    public static double doubles(double origin, double bound) {
        double v = random.get().nextDouble();
        log.fine(String.valueOf(v));
        v = v * (bound - origin) + origin;
        return v;
    }

    public static boolean success(double pct) {
        final double v = random.get().nextDouble();
        log.fine(String.valueOf(v));
        return v < pct;
    }

    public static int count(double pct, int times) {
        int sum = 0;
        for (int i = 0; i < times; i++) {
            if (success(pct))
                sum += 1;
        }
        return sum;
    }

    public static int choose(int total) {
        final int i = random.get().nextInt(total);
        log.fine(String.valueOf(i));
        return i;
    }

    public static <T> T choose(Collection<T> collection) {
        if (collection.isEmpty())
            return null;
        final int choosed = choose(collection.size());
        final Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < choosed; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}
