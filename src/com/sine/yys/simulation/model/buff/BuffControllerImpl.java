package com.sine.yys.simulation.model.buff;

import com.sine.yys.simulation.component.Container;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.shield.DiZangXiangShield;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.util.Msg;

import java.util.*;
import java.util.logging.Logger;

public class BuffControllerImpl implements BuffController {
    private final Logger log = Logger.getLogger(getClass().toString());

    private final Set<Container<IBuff>> set = new TreeSet<>();
    private static final Map<Class, Integer> prior = new HashMap<>();

    static {
        prior.put(DiZangXiangShield.class, 100);
    }

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
    public void afterAction(Entity self) {
        final Iterator<Container<IBuff>> iterator = set.iterator();
        for (; iterator.hasNext(); ) {
            final Container<IBuff> buffContainer = iterator.next();
            int last = buffContainer.getObj().getLast() - 1;
            if (last <= 0) {
                iterator.remove();
                log.info(Msg.buffEnd(self, buffContainer.getObj()));
            } else {
                buffContainer.getObj().setLast(last);
            }
        }
    }
}
