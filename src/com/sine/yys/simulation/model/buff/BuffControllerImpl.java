package com.sine.yys.simulation.model.buff;

import com.sine.yys.simulation.component.Container;
import com.sine.yys.simulation.model.shield.DiZangXiangShield;
import com.sine.yys.simulation.model.shield.Shield;

import java.util.*;

public class BuffControllerImpl implements BuffController {
    private final Set<Container<Buff>> set = new TreeSet<>();
    private static final Map<Class, Integer> prior = new HashMap<>();

    static {
        prior.put(DiZangXiangShield.class, 100);
    }

    @Override
    public void addShield(Shield shield) {
        Class clz = shield.getClass();
        for (Container<Buff> buffContainer : set) {
            if (buffContainer.getObj().getClass() == clz) {
                if (((Shield) buffContainer.getObj()).getValue() < shield.getValue())
                    buffContainer.setObj(shield);
                return;
            }
        }
        set.add(new Container<>(prior.get(shield.getClass()), shield));
    }

    @Override
    public List<Shield> getShields() {
        List<Shield> list = new ArrayList<>();
        for (Container<Buff> buffContainer : set) {
            if (buffContainer.getObj() instanceof Shield) {
                list.add((Shield) buffContainer.getObj());
            }
        }
        return list;
    }
}
