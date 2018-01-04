package com.sine.yys.simulation.util;

import com.sine.yys.simulation.component.Context;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.logging.Logger;

public class Msg {
    private static final Logger log = Logger.getLogger(Msg.class.toString());

    private static Context context = null;

    public static void setContext(Context o) {
        context = o;
    }

    public static String kill() {
        return vector(1, 1, "击杀", 1, 1, "");
    }

    public static String turn() {
        return discribe(1, 1, "行动");
    }

    public static String info(String msg) {
        return discribe(1, 1, msg);
    }

    public static String action() {
        return discribe(1, 1, "使用了 " + context.getActiveSkill().getName());
    }

    public static String damage(Entity target, int damage) {
        return vector(1, 1, "攻击", 1, 1, String.format("造成% 5d伤害", damage));
    }

    public static String trigger(Skill skill) {
        return discribe(1, 1, "触发 " + skill.getName());
    }

    public static String trigger(Mitama mitama) {
        return discribe(1, 1, "触发 " + mitama.getName());
    }

    public static String grabFire(int num) {
        return vector(1, 0, "吸取", 1, 0, num + " 点鬼火，还剩 " + context.getOwn().getFire() + " 点");
    }

    public static String addFire(int num) {
        return discribe(1, 0, "行动满5回合，回复 " + num + " 点鬼火，还剩 " + context.getOwn().getFire() + " 点");
    }

    public static String useFire(int num) {
        return discribe(1, 1, "消耗了 " + num + " 点鬼火，还剩 " + context.getOwn().getFire() + " 点");
    }

    private static final int campWidth = 8;
    private static final int entityWidth = -9;
    private static final int predicateWidth = -10;

    /**
     * int值为1则显示。
     *
     * @param o 己方
     * @param s 自己
     * @param v 谓语
     * @param e 敌方
     * @param t 目标
     * @param m 宾语
     */
    private static String vector(int o, int s, String v, int e, int t, String m) {
        String own = chStr(o == 1 ? ("[" + context.getOwn().getName() + "]") : "", campWidth);
        String self = chStr(s == 1 ? context.getSelf().getName() : "", entityWidth);
        String predicate = chStr(v, predicateWidth);
        String enemy = chStr(e == 1 ? ("[" + context.getEnemy().getName() + "]") : "", campWidth);
        String target = chStr(t == 1 ? context.getTarget().getName() : "", entityWidth);
        return String.format("%s%s%s%s%s%s", own, self, predicate, enemy, target, m);
    }

    private static String discribe(int o, int s, String v) {
        String own = chStr(o == 1 ? ("[" + context.getOwn().getName() + "]") : "", campWidth);
        String self = chStr(s == 1 ? context.getSelf().getName() : "", entityWidth);
        return String.format("%s%s%s", own, self, v);
    }

    private static String formatCampEntity(Camp camp, Entity entity) {
        return chStr("[" + camp.getName() + "]", 8) + chStr(entity.getName(), -9);
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
