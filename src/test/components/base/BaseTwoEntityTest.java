package test.components.base;

import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shikigami.BaseShikigami;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.simulation.BaseCamp;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import test.components.TestProperty;

import java.util.Collections;

/**
 * 两个式神对打的测试，只有一方出手。
 */
public abstract class BaseTwoEntityTest implements Test {
    protected final EnInfo i1 = new EnInfo();
    protected final EnInfo i2 = new EnInfo();
    protected final BaseCamp c1 = new PVPCamp(redName, 0);
    protected final BaseCamp c2 = new PVPCamp(buleName, 0);
    protected Simulator simulator;
    protected ShikigamiEntityImpl e1;
    protected ShikigamiEntityImpl e2;

    /**
     * 自定义具体设置，设置式神、属性、御魂。
     */
    protected abstract void init();

    /**
     * 初始化战斗环境后调用，执行测试逻辑。
     */
    protected abstract void dotest();

    @Override
    public void test() throws AssertionError {
        i1.property.speed = 10000;
        i2.property.speed = 1;
        init();
        e1 = new ShikigamiEntityImpl(i1.shikigami, i1.property, i1.mitama, 1.0, i1.shikigami.getName());
        c1.addEntity(e1);
        e2 = new ShikigamiEntityImpl(i2.shikigami, i2.property, i2.mitama, 1.0, i2.shikigami.getName());
        c2.addEntity(e2);
        simulator = new Simulator(c1, c2, Collections.EMPTY_LIST);
        simulator.init();
        dotest();
    }

    protected class EnInfo {
        public BaseShikigami shikigami = ShikigamiFactory.create("雨女");
        public TestProperty property = new TestProperty();
        public BaseMitama mitama = null;
    }
}
