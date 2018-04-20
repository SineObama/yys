package test.components.base;

import com.sine.yys.EntityInfo;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.mitama.MitamaFactory;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.simulation.BaseCamp;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import test.property.Property1;

import java.util.Collections;

/**
 * 两个式神对打的测试，只有一方出手。
 */
public abstract class BaseTwoEntityTest implements Test {
    protected EntityInfo first = new EntityInfo();
    protected EntityInfo second = new EntityInfo();
    protected BaseCamp red;
    protected BaseCamp blue;
    protected Simulator simulator;
    protected ShikigamiEntityImpl firstEnt;
    protected ShikigamiEntityImpl secondEnt;

    protected void before() {
        first.property = new Property1(10000);
        second.property = new Property1(1);
        red = new PVPCamp(redName, 0);
        blue = new PVPCamp(buleName, 0);
        firstEnt = new ShikigamiEntityImpl(ShikigamiFactory.create(first.shiShen), first.property, MitamaFactory.create(first.mitama), 1.0, ShikigamiFactory.getDefaultName(first.shiShen));
        red.addEntity(firstEnt);
        secondEnt = new ShikigamiEntityImpl(ShikigamiFactory.create(second.shiShen), second.property, MitamaFactory.create(second.mitama), 1.0, ShikigamiFactory.getDefaultName(second.shiShen));
        blue.addEntity(secondEnt);
        simulator = new Simulator(red, blue, Collections.EMPTY_LIST);
        simulator.init();
    }
}
