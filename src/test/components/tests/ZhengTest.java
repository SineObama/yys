package test.components.tests;

import com.sine.yys.inter.Entity;
import com.sine.yys.mitama.Zheng;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试狰的效果。
 */
public class ZhengTest extends BaseTwoEntityTest {
    private double pct = 1.0;
    private final Zheng zheng = new Zheng() {
        @Override
        public double getPct(Entity unused) {
            return pct;
        }
    };

    @Override
    protected void init() {
        i2.mitama = zheng;
    }

    @Override
    protected void doTest() {
        LifeTest test = new LifeTest(e1);

        simulator.step();
        test.test(-com(e2), "+");
    }

    @Override
    public String getLabels() {
        return "御魂 狰";
    }
}
