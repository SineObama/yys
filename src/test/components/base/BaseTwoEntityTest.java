package test.components.base;

import com.sine.yys.entity.ShikigamiEntityImpl;

/**
 * 两个式神对打的测试，只有一方出手。
 */
public abstract class BaseTwoEntityTest extends BaseTest implements Test {
    protected final EnInfo i1 = new EnInfo();
    protected final EnInfo i2 = new EnInfo();
    protected ShikigamiEntityImpl e1;
    protected ShikigamiEntityImpl e2;

    /**
     * 自定义具体设置，设置式神、属性、御魂。
     */
    protected abstract void init();

    /**
     * 构造战斗环境后被调用，执行测试逻辑。
     */
    protected abstract void doTest();

    @Override
    public final void test() throws AssertionError {
        i1.property.speed = 10000;
        i2.property.speed = 1;
        init();
        e1 = form(i1);
        _list1.add(e1);
        e2 = form(i2);
        _list2.add(e2);
        initSimulation();
        doTest();
    }
}
