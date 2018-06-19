package com.sine.yys.impl;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.control.*;
import com.sine.yys.buff.shield.BangJingShield;
import com.sine.yys.buff.shield.DiZangXiangShield;
import com.sine.yys.buff.shield.XueZhiHuaHaiShield;
import com.sine.yys.inter.BuffController;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.rule.buff.*;
import com.sine.yys.util.Msg;

import java.util.*;
import java.util.logging.Logger;

// XXXX buff的先后顺序与效率问题，考虑改用LinkList存储，还有优先级获取是否会有多个buff的问题。
// XXXX 连并删除附属buff时会不会有多次调用onRemove产生的问题，约定不写onRemove？
/**
 * 额外给主逻辑提供行动前后调用的接口。
 */
public class BuffControllerImpl implements BuffController {
    private static final Map<Class, Integer> prior = new HashMap<>();

    /*
     * 优先级小的在前面。
     * 对于护盾，先消耗前面的。
     * 对于控制效果，前面的先生效。
     * 以后还可能用于驱散？
     */
    static {
        prior.put(BangJingShield.class, 100);
        prior.put(DiZangXiangShield.class, 200);
        prior.put(XueZhiHuaHaiShield.class, 1000);

        prior.put(BianXing.class, 100000);
        prior.put(XuanYun.class, 100100);
        prior.put(BingDong.class, 100200);
        prior.put(ShuiMian.class, 100300);
        prior.put(StrongChaoFeng.class, 100400);
        prior.put(WeakChaoFeng.class, 100500);
        prior.put(HunLuan.class, 100600);
        prior.put(ChenMo.class, 100700);
    }

    private final Logger log = Logger.getLogger(getClass().getName());

    private List<BaseIBuff> set = new ArrayList<>();
    private final BeDamage beDamage = new BeDamage();
    private final Cure cure = new Cure();
    private final DamageUp damageUp = new DamageUp();
    private final FlagDamage flagDamage = new FlagDamage();
    private final AtkPct atkPct = new AtkPct();
    private final DefPct defPct = new DefPct();
    private final Speed speed = new Speed();
    private final Critical critical = new Critical();
    private final CriticalDamage criticalDamage = new CriticalDamage();
    private final EffectHit effectHit = new EffectHit();
    private final EffectDef effectDef = new EffectDef();

    private final Entity self;

    public BuffControllerImpl(Entity self) {
        this.self = self;
    }

    @Override
    public Object remove(Object obj) {
        final BaseIBuff iBuff = (BaseIBuff) obj;
        if (iBuff != null)
            iBuff.onRemove(self);
        return set.remove(iBuff);
    }

    @Override
    public <T> Collection<T> getWithPrior(Class<T> tClass) {
        Map<Integer, T> sorted = new TreeMap<>();
        for (Object o : set) {
            if (tClass.isAssignableFrom(o.getClass()))
                sorted.put(prior.get(o.getClass()), tClass.cast(o));
        }
        return sorted.values();
    }

    @Override
    public <T> T getFirstWithPrior(Class<T> tClass) {
        final Iterator<T> iterator = getWithPrior(tClass).iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    public void beforeAction(Controller controller) {
        Collection<BaseIBuff> buffs = new ArrayList<>(set);
        for (BaseIBuff buff : buffs) {
            if (self.isDead())
                break;
            if (buff.beforeAction(controller, self) == 0) {
                buff.onRemove(self);
                set.remove(buff);
                log.info(Msg.info(self, buff.getName(), "效果消失了"));
            }
        }
    }

    public void afterAction(Controller controller) {
        Collection<BaseIBuff> buffs = new ArrayList<>(set);
        for (BaseIBuff buff : buffs) {
            if (buff.afterAction(controller, self) == 0) {
                buff.onRemove(self);
                set.remove(buff);
                // TODO 输出信息移到buff中？
                log.info(Msg.info(self, buff.getName(), "效果消失了"));
            }
        }
    }

    @Override
    public <T> void add(Comparable<T> buff0) {
        final BaseIBuff buff = (BaseIBuff) buff0;
        BaseIBuff iBuff = find(buff0.getClass());
        if (iBuff != null) {
            if (iBuff.compareTo(buff) <= 0) {
                set.remove(iBuff);
                set.add(buff);
            }
            return;
        }
        set.add(buff);
    }

    private <T> BaseIBuff find(Class<T> clazz) {
        for (BaseIBuff iBuff : set)
            if (clazz.isAssignableFrom(iBuff.getClass()))
                return iBuff;
        return null;
    }

    @Override
    public <T> T get(Class<T> clazz) {
        return clazz.cast(find(clazz));
    }

    @Override
    public <T> boolean contain(Class<T> clazz) {
        return find(clazz) != null;
    }

    public Collection<BaseIBuff> getAll() {
        return set;
    }

    @Override
    public <T> void remove(Class<T> clazz) {
        remove(find(clazz));
    }

    @Override
    public double getBeDamage() {
        return beDamage.calc(set);
    }

    @Override
    public double getCure() {
        return cure.calc(set);
    }

    @Override
    public double getDamageUp() {
        return damageUp.calc(set);
    }

    @Override
    public double getFlagDamage() {
        return flagDamage.calc(set);
    }

    @Override
    public double getAtkPct() {
        return atkPct.calc(set);
    }

    @Override
    public double getDefPct() {
        return defPct.calc(set);
    }

    @Override
    public double getSpeed() {
        return speed.calc(set);
    }

    @Override
    public double getCritical() {
        return critical.calc(set);
    }

    @Override
    public double getCriticalDamage() {
        return criticalDamage.calc(set);
    }

    @Override
    public double getEffectHit() {
        return effectHit.calc(set);
    }

    @Override
    public double getEffectDef() {
        return effectDef.calc(set);
    }

    @Override
    public void clear() {
        final ArrayList<BaseIBuff> newList = new ArrayList<>(set);
        final Iterator<BaseIBuff> iterator = newList.iterator();
        while (iterator.hasNext()) {
            final BaseIBuff buff = iterator.next();
            if (buff.removeOnDie()) {
                buff.onRemove(self);
                iterator.remove();
            }
        }
        set = newList;
    }

    @Override
    public <T> Collection<T> getBuffs(Class<T> clazz) {
        Collection<T> buffs = new ArrayList<>(5);
        for (BaseIBuff iBuff : set) {
            if (clazz.isAssignableFrom(iBuff.getClass()))
                buffs.add(clazz.cast(iBuff));
        }
        return buffs;
    }
}
