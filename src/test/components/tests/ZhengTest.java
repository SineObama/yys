package test.components.tests;

import com.sine.yys.inter.Entity;
import com.sine.yys.mitama.ZhenNv;
import com.sine.yys.mitama.Zheng;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试狰的效果，包括针女反击2次。
 */
public class ZhengTest extends BaseTwoEntityTest {
    private double pct = 1.0;
    private double znPct = 1.0;
    private final Zheng zheng = new Zheng() {
        @Override
        public double getPct(Entity unused) {
            return pct;
        }
    };
    private final ZhenNv zhenNv = new ZhenNv() {
        @Override
        public double getPct() {
            return znPct;
        }
    };

    @Override
    protected void init() {
        i1.mitama = zhenNv;
        i2.mitama = zheng;
    }

    @Override
    protected void doTest() {
        LifeTest test = new LifeTest(e1);

        simulator.step();
        test.test(-com(e2), "+");

        i1.property.critical = 1.0;
        simulator.step();
        test.test(-com(e2) * 2, "+2");
    }

    @Override
    public String getLabels() {
        return "御魂 狰";
    }
}
