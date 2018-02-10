package com.sine.yys.simulation.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * 随机 实用类。
 */
public class RandUtil {
    private static Random random = new Random();

    public static boolean success(double pct) {
        return random.nextDouble() < pct;
    }

    public static boolean fail(double pct) {
        return random.nextDouble() > pct;
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
        return random.nextInt(total);
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
