package com.sine.yys.util;

import java.util.Map;

/**
 * 定义一些辅助函数。
 */
public class Helper {
    public static <T> T findKeyBySubClassAndRemove(Map<?, ?> map, Class<T> tClass) {
        for (Object k : map.keySet()) {
            if (tClass.isAssignableFrom(k.getClass())) {
                map.remove(k);
                return tClass.cast(k);
            }
        }
        return null;
    }
}
