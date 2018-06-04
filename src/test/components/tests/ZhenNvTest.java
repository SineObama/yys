package test.components.tests;

import com.sine.yys.mitama.ZhenNv;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试针女效果。
 */
public class ZhenNvTest extends BaseTwoEntityTest {
    private ZhenNv zhenNv = new ZhenNv() {
        @Override
        public double getPct() {
            return 1.0;
        }
    };

    @Override
    protected void init() {
        i1.mitama = zhenNv;
    }

    @Override
    public void doTest() {
        LifeTest test = new LifeTest(e2);

        simulator.step();
        test.test(-com(e1), "-");

        i1.property.critical = 1.0;
        simulator.step();
        double max1 = e2.getMaxLife() * zhenNv.getMaxDamageByMaxLife();
        double max2 = e1.getAttack() * zhenNv.getMaxDamageByAttack();
        test.test(-com(e1) * e1.getCriticalDamage() - Double.min(max1, max2), "+");
    }

    @Override
    public String getLabels() {
        return "御魂 针女";
    }
}
