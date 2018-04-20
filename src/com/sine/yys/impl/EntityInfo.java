package com.sine.yys.impl;

import com.sine.yys.mitama.MitamaFactory;
import com.sine.yys.shikigami.ShikigamiFactory;

/**
 * 式神实体信息。
 * <p>
 * 使用字符串指定式神种类、御魂种类。允许无御魂（null）。
 */
public class EntityInfo {
    public String shiShen;
    public PropertyImpl property;
    public String mitama;

    public static EntityInfo fromString(String s) throws IllegalArgumentException {
        final String[] tokens = s.split("\\s+");
        if (tokens.length != 10 && tokens.length != 9)
            throw new IllegalArgumentException("wrong fields ：" + String.join(" ", tokens));
        EntityInfo info = new EntityInfo();
        info.shiShen = tokens[0];
        info.property = new PropertyImpl(fromString(tokens, 1, 8));
        info.mitama = tokens.length == 10 ? tokens[9] : null;
        if (!ShikigamiFactory.isSupport(info.shiShen))
            throw new IllegalArgumentException("unsupport shikigami：" + info.shiShen);
        if (!MitamaFactory.isSupport(info.mitama))
            throw new IllegalArgumentException("unsupport mitama：" + info.mitama);
        return info;
    }

    /**
     * 把数组中指定位置开始的若干个字符串转为浮点，简单支持百分号类型。
     */
    private static double[] fromString(String[] s, int begin, int length) {
        double[] doubles = new double[length];
        for (int i = 0; i < length; i++) {
            String string = s[begin + i];
            try {
                if (string.endsWith("%"))
                    doubles[i] = Double.valueOf(string.substring(0, string.length() - 1)) / 100.0;
                else
                    doubles[i] = Double.valueOf(string);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("can't read as double: " + string);
            }
        }
        return doubles;
    }

    @Override
    public String toString() {
        return ShikigamiFactory.getDefaultName(shiShen) +
                ' ' +
                property.toString() +
                ' ' +
                MitamaFactory.getDefaultName(mitama);
    }
}
