package test.components.tests;

import com.sine.yys.mitama.DiZangXiang;
import test.components.base.BaseTwoEntityTest;

public class DiZangXiangTest extends BaseTwoEntityTest {
    private final DiZangXiang diZangXiang = new DiZangXiang();

    @Override
    protected void init() {
        i2.mitama = diZangXiang;
    }

    @Override
    protected void doTest() {
        LifeTest test = new LifeTest(e2);
        simulator.step();
        test.test(-com(e1), "地藏像-1");
        i1.property.critical = 1.0;
        simulator.step();
        test.test(-comc(e1), "地藏像-2");
        test.addShield(e2.getMaxLife() * diZangXiang.getShieldByMaxLife());
        simulator.step();
        test.test(-comc(e1), "地藏像+");
    }

    @Override
    public String getLabels() {
        return "御魂 地藏像";
    }
}
