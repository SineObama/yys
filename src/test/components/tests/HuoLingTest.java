package test.components.tests;

import com.sine.yys.mitama.HuoLing;
import test.components.base.BaseTwoEntityTest;

/**
 * 测试开场火灵效果。
 */
public class HuoLingTest extends BaseTwoEntityTest {
    private final HuoLing huoLing = new HuoLing();
    private int fire;

    @Override
    protected void init() {
        i1.mitama = huoLing;
        fire = c1.getFire();
    }

    @Override
    public void doTest() {
        assert c1.getFire() == fire + huoLing.getAddFire() : "+";
    }

    @Override
    public String getLabels() {
        return "御魂 火灵";
    }
}
