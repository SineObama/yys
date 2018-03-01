package com.sine.yys.simulation;

import com.sine.yys.inter.Mitama;
import com.sine.yys.mitama.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 御魂工厂。
 * 不区分英文大小写。
 */
public class MitamaFactory {
    private static final Map<String, Creator> map = new HashMap<>();

    static {
        put(BangJing::new);
        put(DiZangXiang::new);
        put(HuoLing::new);
        put(MeiYao::new);
        put(PoShi::new);
        put(XinYan::new);
        put(ZhenNv::new);
    }

    private static void put(Creator creator, String... keys) {
        Mitama mitama = creator.create();
        map.put(mitama.getName().toLowerCase(), creator);
        map.put(mitama.getClass().getName().replaceAll(".*\\.", "").toLowerCase(), creator);
        for (String key : keys) {
            map.put(key.toLowerCase(), creator);
        }
    }

    static Mitama create(String key) {
        return map.get(key.toLowerCase()).create();
    }

    static Set<String> keySet() {
        return map.keySet();
    }

    interface Creator {
        Mitama create();
    }
}
