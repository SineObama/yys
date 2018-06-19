package test.components.tests;

import com.sine.yys.mitama.XinYan;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试各临界值下心眼的触发效果。
 */
public class XinYanTest extends BaseTwoEntityTest {
    private XinYan xinYan = new XinYan();

    @Override
    protected void init() {
        i1.mitama = xinYan;
    }

    @Override
    public void doTest() {
        LifeTest test = new LifeTest(e2);

        simulator.step();
        test.test(-com(e1), "-1");

        test.setLife(e2.getMaxLife() * 0.3 + 1);
        simulator.step();
        test.test(-com(e1), "-2");

        test.setLife(e2.getMaxLife() * 0.3);
        simulator.step();
        test.test(-com(e1) * xinYan.getCoefficient(), "+1");

        test.setLife(e2.getMaxLife() * 0.3 - 1);
        simulator.step();
        test.test(-com(e1) * xinYan.getCoefficient(), "+2");
    }

    @Override
    public String getLabels() {
        return "御魂 心眼";
    }
}
