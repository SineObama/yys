package com.sine.yys.simulation.component.core.model;

import com.sine.yys.simulation.component.core.model.buff.Buff;
import com.sine.yys.simulation.component.core.model.buff.Debuff;
import com.sine.yys.simulation.component.core.model.buff.IBuff;
import com.sine.yys.simulation.component.core.model.buff.UniqueIBuff;
import com.sine.yys.simulation.component.core.model.buff.debuff.ControlBuff;
import com.sine.yys.simulation.component.core.model.buff.debuff.HunLuan;
import com.sine.yys.simulation.component.core.model.buff.debuff.SealMitama;
import com.sine.yys.simulation.component.core.model.buff.debuff.SealPassive;
import com.sine.yys.simulation.component.core.model.shield.BangJingShield;
import com.sine.yys.simulation.component.core.model.shield.DiZangXiangShield;
import com.sine.yys.simulation.component.core.model.shield.Shield;
import com.sine.yys.simulation.component.core.model.shield.XueZhiHuaHaiShield;
import com.sine.yys.simulation.info.Target;
import com.sine.yys.simulation.util.Msg;

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
    private final Set<Container<UniqueIBuff>> attach = new TreeSet<>(); // 附属buff，如龙首之玉的防御和抵抗，所有式神公用一个引用。

    @Override
    public void addShield(Shield shield) {
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

    @Override
    public void removeShield(Shield shield) {
        set.remove(new Container<>((IBuff) shield));
    }

    @Override
    public List<Shield> getShields() {
        List<Shield> list = new ArrayList<>();
        for (Container<IBuff> buffContainer : set) {
            if (buffContainer.getObj() instanceof Shield) {
                list.add((Shield) buffContainer.getObj());
            }
        }
        return list;
    }

    @Override
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
    public void addBuff(Buff buff) {
        addIBuff(buff);
    }

    @Override
    public void addDebuff(Debuff debuff) {
        addIBuff(debuff);
    }

    @Override
    public <T extends UniqueIBuff> T getUnique(Class<T> clazz) {
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
    public void addAttach(UniqueIBuff buff) {
        Class clz = buff.getClass();
        for (Container<UniqueIBuff> buffContainer : attach) {
            if (buffContainer.getObj().getClass() == clz) {
                return;
            }
        }
        attach.add(new Container<>(prior.get(buff.getClass()), buff));
    }

    private void addIBuff(IBuff iBuff) {
        if (iBuff instanceof UniqueIBuff) {
            Class clz = iBuff.getClass();
            for (Container<IBuff> buffContainer : set) {
                if (buffContainer.getObj().getClass() == clz) {
                    if (buffContainer.getObj().getLast() < iBuff.getLast())
                        buffContainer.setObj(iBuff);
                    return;
                }
            }
        }
        set.add(new Container<>(prior.get(iBuff.getClass()), iBuff));
    }

    @Override
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
    public <T extends UniqueIBuff> void removeAttach(Class<T> clazz) {
        for (Container<UniqueIBuff> iBuffContainer : attach) {
            if (iBuffContainer.getObj().getClass() == clazz) {
                attach.remove(iBuffContainer);
                return;
            }
        }
    }

    @Override
    public double getAtkPct() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getAtkPct();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getAtkPct();
        return sum;
    }

    @Override
    public double getDefPct() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getDefPct();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getDefPct();
        return sum;
    }

    @Override
    public double getSpeed() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getSpeed();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getSpeed();
        return sum;
    }

    @Override
    public double getCritical() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getCritical();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getCritical();
        return sum;
    }

    @Override
    public double getCriticalDamage() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getCriticalDamage();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getCriticalDamage();
        return sum;
    }

    @Override
    public double getEffectHit() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getEffectHit();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getEffectHit();
        return sum;
    }

    @Override
    public double getEffectDef() {
        double sum = 0;
        for (Container<IBuff> container : set)
            sum += container.getObj().getEffectDef();
        for (Container<UniqueIBuff> container : attach)
            sum += container.getObj().getEffectDef();
        return sum;
    }
}
