package com.sine.yys.simulation.component;

import com.sine.yys.buff.BattleFlag;
import com.sine.yys.buff.debuff.control.*;
import com.sine.yys.buff.shield.BangJingShield;
import com.sine.yys.buff.shield.DiZangXiangShield;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.buff.shield.XueZhiHuaHaiShield;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.IBuffProperty;
import com.sine.yys.rule.buff.*;
import com.sine.yys.util.Msg;

import java.util.*;
import java.util.logging.Logger;

/**
 * 通过{@link IBuffProperty}返回所有buff相应属性的合计。
 * <p>
 * 给主逻辑提供行动前后调用的接口。
 */
public class BuffControllerImpl implements BuffController, IBuffProperty {
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

    private final Set<IBuff> set = new HashSet<>();
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

    BuffControllerImpl(Entity self) {
        this.self = self;
    }

    @Override
    public Object remove(Object obj) {
        final IBuff iBuff = (IBuff) obj;
        if (iBuff != null)
            iBuff.onRemove(self);
        return set.remove(iBuff);
    }

    /**
     * 按照消耗顺序返回。
     */
    public Collection<Shield> getShields() {
        Map<Integer, Shield> sorted = new TreeMap<>();
        for (Object o : set) {
            if (o instanceof Shield)
                sorted.put(prior.get(o.getClass()), (Shield) o);
        }
        return sorted.values();
    }

    public void beforeAction(Controller controller) {
        Collection<IBuff> buffs = new ArrayList<>(set);
        for (IBuff buff : buffs) {
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
        Collection<IBuff> buffs = new ArrayList<>(set);
        for (IBuff buff : buffs) {
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
        final IBuff buff = (IBuff) buff0;
        for (IBuff iBuff : set) {
            if (iBuff.getClass() == buff0.getClass()) {
                if (iBuff.compareTo(buff) <= 0) {
                    set.remove(iBuff);
                    set.add(buff);
                }
                return;
            }
        }
        set.add(buff);
    }

    private <T> IBuff find(Class<T> clazz) {
        for (IBuff iBuff : set) {
            if (iBuff.getClass() == clazz) {
                return iBuff;
            }
        }
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

    public Collection<IBuff> getAll() {
        return set;
    }

    /**
     * 获取行动控制效果，按控制优先级返回。
     */
    @Override
    public ControlBuff getFirstControlBuff() {
        Map<Integer, ControlBuff> list = new TreeMap<>();
        for (IBuff buff : set) {
            if (buff instanceof ControlBuff)
                list.put(prior.get(buff.getClass()), (ControlBuff) buff);
        }
        final Iterator<ControlBuff> iterator = list.values().iterator();
        if (iterator.hasNext())
            return iterator.next();
        return null;
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
        final BattleFlag battleFlag = get(BattleFlag.class);
        for (IBuff iBuff : new ArrayList<>(set))
            if (iBuff != battleFlag)
                iBuff.onRemove(self);
        set.clear();
        if (battleFlag != null)
            set.add(battleFlag);
    }

    @Override
    public <T> Collection<T> getBuffs(Class<T> clazz) {
        Collection<T> buffs = new ArrayList<>(5);
        for (IBuff iBuff : set) {
            if (clazz.isAssignableFrom(iBuff.getClass()))
                buffs.add(clazz.cast(iBuff));
        }
        return buffs;
    }
}
