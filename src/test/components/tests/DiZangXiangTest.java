package test.components.tests;

import com.sine.yys.mitama.DiZangXiang;
import test.components.base.BaseThreeEntityTest;
import test.components.util.LifeTest;

/**
 * 测试地藏像盾的效果，包括队友。
 */
public class DiZangXiangTest extends BaseThreeEntityTest {
    private double pct = 1.0;
    private final DiZangXiang diZangXiang = new DiZangXiang() {
        @Override
        public double getPct() {
            return pct;
        }
    };

    @Override
    protected void init() {
        i2.mitama = diZangXiang;
    }

    @Override
    protected void doTest() {
        LifeTest test = new LifeTest(e2);
        LifeTest test3 = new LifeTest(e3);

        simulator.step();
        test.test(-com(e1), "-1");

        targetSelector.setName(e3);
        simulator.step();
        test3.test(-com(e1), "队友-");

        targetSelector.setName(e2);
        i1.property.critical = 1.0;
        simulator.step();
        test.test(-comc(e1), "-2");
        test.addShield(e2.getMaxLife() * diZangXiang.getShieldByMaxLife());
        test3.addShield(e2.getMaxLife() * diZangXiang.getShieldByMaxLife());

        simulator.step();
        test.test(-comc(e1), "+");

        targetSelector.setName(e3);
        simulator.step();
        test3.test(-comc(e1), "队友+");
    }

    @Override
    public String getLabels() {
        return "御魂 地藏像";
    }
}
