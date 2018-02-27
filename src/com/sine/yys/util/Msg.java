package com.sine.yys.util;

import com.sine.yys.info.Named;
import com.sine.yys.info.Target;

/**
 * 用于统一输出信息（对齐）。
 */
public class Msg {
    private static final int fullNameWidth = -16;
    private static final int predicateWidth = -10;

    /**
     * 描述性消息。谁做什么。
     */
    public static String info(Target self, String msg) {
        return chStr(self != null ? self.getFullName() : "", fullNameWidth) + msg;
    }

    public static String damage(Target self, Target target, int damage) {
        return vector(self, "对", target, String.format("造成% 5d伤害", damage));
    }

    public static String damage(Target self, Target target, int damage, boolean critical) {
        if (critical)
            return vector(self, "对", target, String.format("造成% 5d暴击伤害", damage));
        return vector(self, "对", target, String.format("造成% 5d伤害", damage));
    }

    public static String addBuff(Target self, Target target, Named buff) {
        return vector(self, "给", target, "添加 " + buff.getName());
    }

    public static String noDamage(Target self, Target target) {
        return vector(self, "攻击", target, "未造成伤害");
    }

    public static String trigger(Target target, Named named) {
        return info(target, "触发 " + named.getName());
    }

    /**
     * 指向性消息。谁对谁做什么。
     */
    public static String vector(Target self, String v, Target target, String m) {
        String selfn = chStr(self != null ? self.getFullName() : "", fullNameWidth);
        String predicate = chStr(v, predicateWidth);
        String targetn = chStr(target != null ? target.getFullName() : "", fullNameWidth);
        return String.format("%s%s%s%s", selfn, predicate, targetn, m);
    }

    /**
     * 为包含中文（宽度2）的字符串，格式化到指定宽度。
     */
    public static String chStr(final String string, int width) {
        final boolean left = width < 0;
        if (left)
            width = -width;
        char[] chs = string.toCharArray();
        for (char ch : chs) {
            if (isChinese(ch))
                width -= 1;
        }
        return String.format("%" + (left ? "-" : "") + width + "s", string);
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}
