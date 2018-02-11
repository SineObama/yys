package com.sine.yys.simulation.util;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.buff.IBuff;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.logging.Logger;

/**
 * 用于统一输出信息（对齐）。
 */
public class Msg {
    private static final Logger log = Logger.getLogger(Msg.class.toString());

    public static String kill(Entity self, Entity target) {
        return vector(self, "击杀", target, "");
    }

    public static String info(Entity self, String msg) {
        String ownn = chStr(self != null && self.getCamp() != null ? ("[" + self.getCamp().getName() + "]") : "", campWidth);
        String selfn = chStr(self != null ? self.getName() : "", entityWidth);
        return String.format("%s%s%s", ownn, selfn, msg);
    }

    public static String buffEnd(Entity self, IBuff iBuff) {
        return info(self, iBuff.getName() + " 效果消失了");
    }

    public static String action(Entity self, ActiveSkill activeSkill) {
        return info(self, "使用了 " + activeSkill.getName());
    }

    public static String damage(Entity self, Entity target, int damage) {
        return vector(self, "对", target, String.format("造成% 5d伤害", damage));
    }

    public static String noDamage(Entity self, Entity target) {
        return vector(self, "攻击", target, "未造成伤害");
    }

    public static String trigger(Skill skill) {
        return info(skill.getSelf(), "触发 " + skill.getName());
    }

    public static String trigger(Mitama mitama) {
        return info(mitama.getSelf(), "触发 " + mitama.getName());
    }

    public static String grabFire(Camp own, Camp enemy, int num) {
        return vector(own, "吸取", enemy, num + " 点鬼火，当前剩余 " + own.getFire() + " 点");
    }

    public static String fiveRound(Camp own, int num) {
        return info(own, "行动满5回合，将回复 " + num + " 点鬼火");
    }

    public static String addFire(Camp own, int num) {
        return info(own, "增加 " + num + " 点鬼火，当前剩余 " + own.getFire() + " 点");
    }

    public static String useFire(Entity self, int num) {
        return info(self, "消耗了 " + num + " 点鬼火，当前剩余 " + self.getCamp().getFire() + " 点");
    }

    /**
     * 描述性消息。谁做什么。
     */
    public static String info(Camp own, String msg) {
        String ownn = chStr(own != null ? ("[" + own.getName() + "]") : "", campWidth);
        String selfn = chStr("", entityWidth);
        return String.format("%s%s%s", ownn, selfn, msg);
    }

    private static final int campWidth = 8;
    private static final int entityWidth = -9;
    private static final int predicateWidth = -10;

    /**
     * 指向性消息。谁对谁做什么。
     *
     * @param own
     * @param self
     * @param v      谓语
     * @param enemy
     * @param target
     * @param m      宾语
     * @return
     */
    private static String vector(Entity self, String v, Entity target, String m) {
        String ownn = chStr(self != null && self.getCamp() != null ? ("[" + self.getCamp().getName() + "]") : "", campWidth);
        String selfn = chStr(self != null ? self.getName() : "", entityWidth);
        String predicate = chStr(v, predicateWidth);
        String enemyn = chStr(target != null && target.getCamp() != null ? ("[" + target.getCamp().getName() + "]") : "", campWidth);
        String targetn = chStr(target != null ? target.getName() : "", entityWidth);
        return String.format("%s%s%s%s%s%s", ownn, selfn, predicate, enemyn, targetn, m);
    }

    /**
     * 阵营消息。
     *
     * @param own
     * @param v
     * @param enemy
     * @param m
     * @return
     */
    private static String vector(Camp own, String v, Camp enemy, String m) {
        String ownn = chStr(own != null ? ("[" + own.getName() + "]") : "", campWidth);
        String selfn = chStr("", entityWidth);
        String predicate = chStr(v, predicateWidth);
        String enemyn = chStr(enemy != null ? ("[" + enemy.getName() + "]") : "", campWidth);
        String targetn = chStr("", entityWidth);
        return String.format("%s%s%s%s%s%s", ownn, selfn, predicate, enemyn, targetn, m);
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
