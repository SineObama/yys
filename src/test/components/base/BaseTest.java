package test.components.base;

import com.sine.yys.entity.EntityImpl;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.entity.SimpleObject;
import com.sine.yys.inter.base.Named;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shikigami.BaseShikigami;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import test.components.TestProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 作为基类保有一些变量，定义测试过程（一些抽象函数），进行阵营的装填和模拟初始化等通用逻辑。
 * 也提供一些辅助函数。
 */
public abstract class BaseTest implements Test {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    protected final PVPCamp c1 = new PVPCamp(redName, 0);
    protected final PVPCamp c2 = new PVPCamp(buleName, 0);
    protected final List<ShikigamiEntityImpl> _list1 = new ArrayList<>();
    protected final List<ShikigamiEntityImpl> _list2 = new ArrayList<>();
    protected final List<SimpleObject> _extra = new ArrayList<>();
    protected Simulator simulator;

    protected static ShikigamiEntityImpl form(EnInfo i) {
        return new ShikigamiEntityImpl(i.shikigami, i.property, i.mitama, 1.0, i.getName());
    }

    /**
     * @return 普通攻击数值=攻击*普攻系数。
     */
    protected static double com(EntityImpl entity) {
        return entity.getAttack() * entity.getCommonAttack().getCoefficient();
    }

    /**
     * @return 暴击的普通攻击数值=攻击*普攻系数*暴击伤害。
     */
    protected static double comc(EntityImpl entity) {
        return entity.getAttack() * entity.getCommonAttack().getCoefficient() * entity.getCriticalDamage();
    }

    /**
     * 基类进行通用设置。
     */
    abstract void beforeInit();

    /**
     * 自定义具体设置，设置式神、属性、御魂。
     */
    protected abstract void init();

    /**
     * 基类进行通用设置。
     */
    abstract void afterInit();

    @Override
    public final void test() throws AssertionError {
        beforeInit();
        init();
        afterInit();
        for (ShikigamiEntityImpl entity : _list1)
            c1.addEntity(entity);
        for (ShikigamiEntityImpl entity : _list2)
            c2.addEntity(entity);
        simulator = new Simulator(c1, c2, _extra);
        simulator.init();
        log.info("初始化模拟完毕");
        doTest();
    }

    /**
     * 构造战斗环境后被调用，执行测试逻辑。
     */
    protected abstract void doTest();

    protected class EnInfo implements Named {
        public BaseShikigami shikigami = ShikigamiFactory.create("雨女");
        public TestProperty property = new TestProperty();
        public BaseMitama mitama = null;
        public String name = null;

        public EnInfo() {
        }

        public EnInfo(BaseShikigami shikigami, TestProperty property, BaseMitama mitama, String name) {
            this.shikigami = shikigami;
            this.property = property;
            this.mitama = mitama;
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name != null ? this.name : this.shikigami.getName();
        }
    }
}
