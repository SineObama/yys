package com.sine.yys.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 随机 实用类。
 */
public class RandUtil {
    private static final Logger log = Logger.getLogger(RandUtil.class.getName());
    private static final Random random;

    static {
        final long seed = Seed.get();
        log.fine("using random seed:" + seed);
        random = new Random(seed);
    }

    public static boolean success(double pct) {
        final double v = random.nextDouble();
        log.info(String.valueOf(v));
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
        final int i = random.nextInt(total);
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
