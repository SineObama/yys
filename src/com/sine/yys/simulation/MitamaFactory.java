package com.sine.yys.simulation;

import com.sine.yys.inter.base.Mitama;
import com.sine.yys.mitama.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 御魂工厂。
 * <p>
 * 定义了字符串到{@linkplain Mitama 御魂}的对应关系，以进行创建。
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
        put(TiHun::new);
        put(ZhaoCaiMao::new);
        put(Zheng::new);
        put(ShuYao::new);
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
        if (key == null)
            return null;
        final Creator creator = map.get(key.toLowerCase());
        if (creator == null)
            throw new RuntimeException("can't found mitama:" + key);
        return creator.create();
    }

    static Set<String> keySet() {
        return map.keySet();
    }

    interface Creator {
        Mitama create();
    }
}
