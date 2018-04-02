package com.sine.yys.shikigami;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 式神工厂。
 * <p>
 * 定义了字符串到{@linkplain BaseShikigami 式神}的对应关系，以进行创建。
 * 不区分英文大小写。
 */
public class ShikigamiFactory {
    private static final Map<String, Creator> map = new HashMap<>();
    private static final Map<String, String> names = new HashMap<>();

    static {
        put(BiAnHua::new);
        put(BoRe::new);
        put(GuHuoNiao::new);
        put(HuiYeJi::new);
        put(LianYou::new);
        put(QingXingDeng::new);
        put(HuaNiaoJuan::new);
        put(RiHeFang::new);
        put(YuNv::new);
        put(JiaoTu::new);
        put(TongNv::new);
        put(XueTongZi::new);
    }

    private static void put(Creator creator, String... keys) {
        BaseShikigami shikigami = creator.create();
        final String defaultName = shikigami.getName().toLowerCase();
        map.put(defaultName, creator);
        names.put(defaultName, defaultName);
        final String pinYin = shikigami.getClass().getSimpleName().toLowerCase();
        map.put(pinYin, creator);
        names.put(pinYin, defaultName);
        for (String key : keys) {
            map.put(key.toLowerCase(), creator);
        }
    }

    public static BaseShikigami create(String key) {
        final Creator creator = map.get(key.toLowerCase());
        if (creator == null)
            throw new RuntimeException("can't found shikigami:" + key);
        return creator.create();
    }

    public static String getDefaultName(String key) {
        return names.get(key);
    }

    static Set<String> keySet() {
        return map.keySet();
    }

    interface Creator {
        BaseShikigami create();
    }
}
