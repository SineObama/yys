package com.sine.yys.simulation;

import com.sine.yys.inter.Mitama;
import com.sine.yys.mitama.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 御魂字典映射。
 * 不区分英文大小写。
 */
public class Mitamas {
    private static final Map<String, Mitama> map = new HashMap<>();

    static {
        put(new BangJing());
        put(new DiZangXiang());
        put(new HuoLing());
        put(new MeiYao());
        put(new PoShi());
        put(new XinYan());
        put(new ZhenNv());
    }

    private static void put(Mitama mitama, String... keys) {
        map.put(mitama.getName().toLowerCase(), mitama);
        map.put(mitama.getClass().getName().replaceAll(".*\\.", "").toLowerCase(), mitama);
        for (String key : keys) {
            map.put(key.toLowerCase(), mitama);
        }
    }

    static final Mitama get(String key) {
        return map.get(key.toLowerCase());
    }

    static final Set<String> keySet() {
        return map.keySet();
    }
}
