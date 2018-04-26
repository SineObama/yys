package test.components.tests;

import com.sine.yys.mitama.BangJing;
import test.components.base.BaseTwoEntityTest;

public class BangJingTest extends BaseTwoEntityTest {
    private final BangJing bangJing = new BangJing();

    @Override
    protected void init() {
        i2.mitama = bangJing;
    }

    @Override
    public void dotest() {
        int life = (int) (e2.getMaxLife() * (1 + bangJing.getShieldByMaxLife()));
        while (life >= e2.getMaxLife()) {
            simulator.step();
            life -= e1.getComCount();
        }
        assert e2.getLifeInt() == life : "蚌精1";
    }

    @Override
    public String getLabels() {
        return "御魂 蚌精";
    }
}
