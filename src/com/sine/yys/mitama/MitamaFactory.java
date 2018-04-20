package com.sine.yys.mitama;

import java.util.HashMap;
import java.util.Map;

/**
 * 御魂工厂。
 * <p>
 * 定义了字符串到{@linkplain BaseMitama 御魂}的对应关系，以进行创建。
 * 不区分英文大小写。
 */
public class MitamaFactory {
    private static final Map<String, Creator> map = new HashMap<>();
    private static final Map<String, String> names = new HashMap<>();

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
        put(MuMei::new);
    }

    private static void put(Creator creator, String... keys) {
        BaseMitama mitama = creator.create();
        final String defaultName = mitama.getName().toLowerCase();
        map.put(defaultName, creator);
        names.put(defaultName, defaultName);
        final String pinYin = mitama.getClass().getSimpleName().toLowerCase();
        map.put(pinYin, creator);
        names.put(pinYin, defaultName);
        for (String key : keys) {
            map.put(key.toLowerCase(), creator);
        }
    }

    public static BaseMitama create(String key) {
        if (key == null)
            return null;
        final Creator creator = map.get(key.toLowerCase());
        if (creator == null)
            throw new RuntimeException("can't found mitama:" + key);
        return creator.create();
    }

    public static String getDefaultName(String key) {
        if (key == null)
            return "";
        return names.get(key.toLowerCase());
    }

    public static boolean isSupport(String key) {
        return key == null || map.containsKey(key.toLowerCase());
    }

    interface Creator {
        BaseMitama create();
    }
}
