package test.components.tests;

import com.sine.yys.mitama.BangJing;
import test.components.base.BaseTwoEntityTest;

public class BangJingTest extends BaseTwoEntityTest {
    @Override
    public String getLabels() {
        return "御魂 蚌精";
    }

    @Override
    public void test() {
        first.shiShen = "雨女";
        second.mitama = "蚌精";
        second.shiShen = "雨女";
        before();
        BangJing bangJing = (BangJing) secondEnt.getMitamas().get(0);
        int life = (int) (secondEnt.getMaxLife() * (1 + bangJing.getShieldByMaxLife()));
        while (life >= secondEnt.getMaxLife()) {
            simulator.step();
            life -= firstEnt.getComCount();
        }
        assert secondEnt.getLifeInt() == life : "蚌精1";
    }
}
