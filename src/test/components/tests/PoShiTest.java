package test.components.tests;

import com.sine.yys.mitama.PoShi;
import test.components.base.BaseTwoEntityTest;

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
        test.test(-com(e1) * poShi.getCoefficient(), "破势+1");
        test.setLife(e2.getMaxLife() * 0.701);
        simulator.step();
        test.test(-com(e1) * poShi.getCoefficient(), "破势+2");
        test.setLife(e2.getMaxLife() * 0.7);
        simulator.step();
        test.test(-com(e1) * poShi.getCoefficient(), "破势+3");
        test.setLife(e2.getMaxLife() * 0.7 - 1);
        simulator.step();
        test.test(-com(e1), "破势-1");
    }

    @Override
    public String getLabels() {
        return "御魂 破势";
    }
}
