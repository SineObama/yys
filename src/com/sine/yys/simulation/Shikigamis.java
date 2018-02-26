package com.sine.yys.simulation;

import com.sine.yys.inter.Shikigami;
import com.sine.yys.shikigami.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 式神字典映射。
 * 不区分英文大小写。
 */
public class Shikigamis {
    private static final Map<String, Shikigami> map = new HashMap<>();

    static {
        put(new BiAnHua());
        put(new BoRe());
        put(new GuHuoNiao());
        put(new HuiYeJi());
        put(new LianYou());
        put(new QingXingDeng());
    }

    private static void put(Shikigami shikigami, String... keys) {
        map.put(shikigami.getName().toLowerCase(), shikigami);
        map.put(shikigami.getClass().toString().replaceAll(".*\\.", "").toLowerCase(), shikigami);
        for (String key : keys) {
            map.put(key.toLowerCase(), shikigami);
        }
    }

    static final Shikigami get(String key) {
        return map.get(key.toLowerCase());
    }

    static final Set<String> keySet() {
        return map.keySet();
    }
}
