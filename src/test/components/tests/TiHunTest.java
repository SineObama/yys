package test.components.tests;

import com.sine.yys.mitama.TiHun;
import test.components.base.BaseThreeEntityTest;
import test.components.util.LifeTest;

/**
 * 测试薙魂的效果。
 */
public class TiHunTest extends BaseThreeEntityTest {
    private double pct = 1.0;
    private final TiHun tiHun = new TiHun() {
        @Override
        public double getPct() {
            return pct;
        }
    };

    @Override
    protected void init() {
        i3.mitama = tiHun;
    }

    @Override
    protected void doTest() {
        LifeTest test = new LifeTest(e2);
        LifeTest test3 = new LifeTest(e3);

        simulator.step();
        double change = com(e1) * (1 - tiHun.getDamageReducePct());
        double shared = change * tiHun.getSharePct();
        test.test(-(change - shared), "+");
        test3.test(-shared, "队友+");
    }

    @Override
    public String getLabels() {
        return "御魂 薙魂";
    }
}
