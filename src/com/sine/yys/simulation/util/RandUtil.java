package com.sine.yys.simulation.util;

import java.util.Random;

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
}
