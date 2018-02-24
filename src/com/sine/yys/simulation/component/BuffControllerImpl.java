package com.sine.yys.simulation.component;

import com.sine.yys.buff.IBuff;
import com.sine.yys.buff.debuff.ControlBuff;
import com.sine.yys.buff.debuff.HunLuan;
import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.buff.shield.BangJingShield;
import com.sine.yys.buff.shield.DiZangXiangShield;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.buff.shield.XueZhiHuaHaiShield;
import com.sine.yys.info.Target;
import com.sine.yys.inter.BuffController;
import com.sine.yys.rule.buff.*;
import com.sine.yys.util.Msg;

import java.util.*;
import java.util.logging.Logger;

/**
 * XXX 实现代码上有些冗余。
 */
public class BuffControllerImpl implements BuffController {
    private static final Map<Class, Integer> prior = new HashMap<>();// TODO buff存储方式

    static {
        prior.put(BangJingShield.class, 100);
        prior.put(DiZangXiangShield.class, 200);
        prior.put(XueZhiHuaHaiShield.class, 1000);

        prior.put(HunLuan.class, 100100);
    }

    private final Logger log = Logger.getLogger(getClass().toString());
    private final Set<Container<IBuff>> set = new TreeSet<>();
    private final Set<Container<IBuff>> attach = new TreeSet<>(); // 附属buff，如龙首之玉的防御和抵抗

    @Override
    public void addShield(Object shield) {
        Class clz = shield.getClass();
        for (Container<IBuff> buffContainer : set) {
            if (buffContainer.getObj().getClass() == clz) {
                if (((Shield) buffContainer.getObj()).getValue() < shield.getValue())
                    buffContainer.setObj(shield);
                return;
            }
        }
        set.add(new Container<>(prior.get(shield.getClass()), shield));
    }

    public void removeShield(Object shield) {
        set.remove(new Container<>((IBuff) shield));
    }

    /**
     * 按照消耗顺序返回。
     */
    public List<Shield> getShields() {
        List<Shield> list = new ArrayList<>();
        for (Container<IBuff> buffContainer : set) {
            if (buffContainer.getObj() instanceof Shield) {
                list.add((Shield) buffContainer.getObj());
            }
        }
        return list;
    }

    /**
     * 行动后执行。
     * 给效果回合数减1，减到0则移除效果。
     */
    public void step(Target self) {
        // XXXX 分开试试
        List<IBuff> removeList = new ArrayList<>();
        final Iterator<Container<IBuff>> iterator = set.iterator();
        for (; iterator.hasNext(); ) {
            final Container<IBuff> buffContainer = iterator.next();
            if (buffContainer.getObj().step() == 0) {
                removeList.add(buffContainer.getObj());
                // TODO 输出信息移到buff中？
                log.info(Msg.info(self, buffContainer.getObj().getName() + " 效果消失了"));
            }
        }
        for (IBuff iBuff : removeList) {
            set.remove(new Container<>(iBuff));
        }
    }

    @Override
    public void addIBuff(Object iBuff) {
        Class clz = iBuff.getClass();
        for (Container<IBuff> buffContainer : set) {
            if (buffContainer.getObj().getClass() == clz) {
                if (buffContainer.getObj().getLast() < iBuff.getLast())
                    buffContainer.setObj(iBuff);
                return;
            }
        }
        set.add(new Container<>(prior.get(iBuff.getClass()), iBuff));
    }

    @Override
    public <T> T getUnique(Class<T> clazz) {
        for (Container<IBuff> container : set) {
            if (container.getObj().getClass() == clazz)
                return (T) container.getObj();  // XXX 又是unchecked
        }
        return null;
    }

    @Override
    public boolean mitamaSealed() {
        for (Container<IBuff> iBuffContainer : set) {
            if (iBuffContainer.getObj() instanceof SealMitama)
                return true;
        }
        return false;
    }

    @Override
    public boolean passiveSealed() {
        for (Container<IBuff> iBuffContainer : set) {
            if (iBuffContainer.getObj() instanceof SealPassive)
                return true;
        }
        return false;
    }

    /**
     * 暂定只有一个buff生效，不会进行替换。
     */
    @Override
    public void addAttach(Object buff) {
        Class clz = buff.getClass();
        for (Container<IBuff> buffContainer : attach) {
            if (buffContainer.getObj().getClass() == clz) {
                return;
            }
        }
        attach.add(new Container<>(prior.get(buff.getClass()), buff));
    }

    /**
     * 获取行动控制效果，按控制优先级返回。
     */
    public List<ControlBuff> getControlBuffs() {
        List<ControlBuff> list = new ArrayList<>();
        for (Container<IBuff> buffContainer : set) {
            if (buffContainer.getObj() instanceof ControlBuff) {
                list.add((ControlBuff) buffContainer.getObj());
            }
        }
        return list;
    }

    @Override
    public <T> void removeAttach(Class<T> clazz) {
        for (Container<IBuff> iBuffContainer : attach) {
            if (iBuffContainer.getObj().getClass() == clazz) {
                attach.remove(iBuffContainer);
                return;
            }
        }
    }

    @Override
    public double getAtkPct() {
        return new AtkPct().calc(set, attach);
    }

    @Override
    public double getDefPct() {
        return new DefPct().calc(set, attach);
    }

    @Override
    public double getSpeed() {
        return new Speed().calc(set, attach);
    }

    @Override
    public double getCritical() {
        return new Critical().calc(set, attach);
    }

    @Override
    public double getCriticalDamage() {
        return new CriticalDamage().calc(set, attach);
    }

    @Override
    public double getEffectHit() {
        return new EffectHit().calc(set, attach);
    }

    @Override
    public double getEffectDef() {
        return new EffectDef().calc(set, attach);
    }
}
