package test.components.tests;

import com.sine.yys.mitama.PoShi;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试各临界值下破势的触发效果。
 */
public class PoShiTest extends BaseTwoEntityTest {
    private PoShi poShi = new PoShi();

    @Override
    protected void init() {
        i1.mitama = poShi;
    }

    @Override
    public void doTest() {
        LifeTest test = new LifeTest(e2);

        simulator.step();
        test.test(-com(e1) * poShi.getCoefficient(), "+1");

        test.setLife(e2.getMaxLife() * 0.701);
        simulator.step();
        test.test(-com(e1) * poShi.getCoefficient(), "+2");

        test.setLife(e2.getMaxLife() * 0.7);
        simulator.step();
        test.test(-com(e1) * poShi.getCoefficient(), "+3");

        test.setLife(e2.getMaxLife() * 0.7 - 1);
        simulator.step();
        test.test(-com(e1), "-");
    }

    @Override
    public String getLabels() {
        return "御魂 破势";
    }
}
