package com.sine.yys.simulation.component;

import com.sine.yys.buff.IBuff;
import com.sine.yys.buff.debuff.*;
import com.sine.yys.buff.shield.BangJingShield;
import com.sine.yys.buff.shield.DiZangXiangShield;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.buff.shield.XueZhiHuaHaiShield;
import com.sine.yys.info.Combinable;
import com.sine.yys.info.IBuffProperty;
import com.sine.yys.inter.BuffController;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.rule.buff.*;
import com.sine.yys.util.Msg;

import java.util.*;
import java.util.logging.Logger;

/**
 * 给主逻辑提供行动前后调用的接口。
 * 通过{@link IBuffProperty}接口返回所有buff相应属性的合计。
 */
public class BuffControllerImpl implements BuffController, IBuffProperty {
    private static final Map<Class, Integer> prior = new HashMap<>();// TODO buff存储方式

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

        prior.put(XuanYun.class, 100100);
        prior.put(HunLuan.class, 101000);
    }

    private final Logger log = Logger.getLogger(getClass().toString());

    private final Map<Class, IBuff> map = new HashMap<>();

    public Object remove(Object shield) {
        return map.remove(shield.getClass());
    }

    /**
     * 按照消耗顺序返回。
     */
    public Collection<Shield> getShields() {
        Map<Integer, Shield> sorted = new TreeMap<>();
        for (Object o : map.values()) {
            if (o instanceof Shield)
                sorted.put(prior.get(o.getClass()), (Shield) o);
        }
        return sorted.values();
    }

    public void beforeAction(Controller controller, Entity self) {
        Collection<IBuff> buffs = new ArrayList<>(map.values());
        for (IBuff buff : buffs) {
            if (buff.beforeAction(controller, self) == 0) {
                map.remove(buff.getClass());
                log.info(Msg.info(self, buff.getName() + " 效果消失了"));
            }
        }
    }

    public void afterAction(Controller controller, Entity self) {
        Collection<IBuff> buffs = new ArrayList<>(map.values());
        for (IBuff buff : buffs) {
            if (buff.afterAction(controller, self) == 0) {
                map.remove(buff.getClass());
                // TODO 输出信息移到buff中？
                log.info(Msg.info(self, buff.getName() + " 效果消失了"));
            }
        }
    }

    @Override
    public <T> void add(Combinable<T> buff) {
        IBuff buff0 = map.get(buff.getClass());
        if (buff0 != null) {
            map.put(buff.getClass(), (IBuff) buff.combineWith((T) buff0));
        } else {
            map.put(buff.getClass(), (IBuff) buff);
        }
    }

    @Override
    public <T> T get(Class<T> clazz) {
        return (T) map.get(clazz);
    }

    @Override
    public boolean mitamaSealed() {
        for (IBuff iBuff : map.values()) {
            if (iBuff instanceof SealMitama)
                return true;
        }
        return false;
    }

    @Override
    public boolean passiveSealed() {
        for (IBuff iBuff : map.values()) {
            if (iBuff instanceof SealPassive)
                return true;
        }
        return false;
    }

    /**
     * 获取行动控制效果，按控制优先级返回。
     */
    public List<ControlBuff> getControlBuffs() {
        List<ControlBuff> list = new ArrayList<>();
        for (IBuff buff : map.values()) {
            if (buff instanceof ControlBuff)
                list.add((ControlBuff) buff);
        }
        return list;
    }

    @Override
    public <T> void remove(Class<T> clazz) {
        map.remove(clazz);
    }

    @Override
    public double getAtkPct() {
        return new AtkPct().calc(map.values());
    }

    @Override
    public double getDefPct() {
        return new DefPct().calc(map.values());
    }

    @Override
    public double getSpeed() {
        return new Speed().calc(map.values());
    }

    @Override
    public double getCritical() {
        return new Critical().calc(map.values());
    }

    @Override
    public double getCriticalDamage() {
        return new CriticalDamage().calc(map.values());
    }

    @Override
    public double getEffectHit() {
        return new EffectHit().calc(map.values());
    }

    @Override
    public double getEffectDef() {
        return new EffectDef().calc(map.values());
    }
}
