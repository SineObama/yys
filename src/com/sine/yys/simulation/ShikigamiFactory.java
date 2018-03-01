package com.sine.yys.simulation;

import com.sine.yys.inter.Shikigami;
import com.sine.yys.shikigami.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 式神工厂。
 * 不区分英文大小写。
 */
public class ShikigamiFactory {
    private static final Map<String, Creator> map = new HashMap<>();

    static {
        put(BiAnHua::new);
        put(BoRe::new);
        put(GuHuoNiao::new);
        put(HuiYeJi::new);
        put(LianYou::new);
        put(QingXingDeng::new);
        put(HuaNiaoJuan::new);
    }

    private static void put(Creator creator, String... keys) {
        Shikigami shikigami = creator.create();
        map.put(shikigami.getName().toLowerCase(), creator);
        map.put(shikigami.getClass().getName().replaceAll(".*\\.", "").toLowerCase(), creator);
        for (String key : keys) {
            map.put(key.toLowerCase(), creator);
        }
    }

    static Shikigami create(String key) {
        final Creator creator = map.get(key.toLowerCase());
        if (creator == null)
            throw new RuntimeException("can't found shikigami:" + key);
        return creator.create();
    }

    static Set<String> keySet() {
        return map.keySet();
    }

    interface Creator {
        Shikigami create();
    }
}
