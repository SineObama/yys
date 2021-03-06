package test.components.tests;

import com.sine.yys.mitama.BangJing;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试蚌精盾的生产。
 */
public class BangJingTest extends BaseTwoEntityTest {
    private final BangJing bangJing = new BangJing();

    @Override
    protected void init() {
        i2.mitama = bangJing;
    }

    @Override
    public void doTest() {
        LifeTest test = new LifeTest(e2);
        test.addShield(e2.getMaxLife() * bangJing.getShieldByMaxLife());
        while (test.getLife() >= e2.getMaxLife()) {
            simulator.step();
            test.test(-com(e1), "");
        }
    }

    @Override
    public String getLabels() {
        return "御魂 蚌精";
    }
}
