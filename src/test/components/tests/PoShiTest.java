package test.components.tests;

import com.sine.yys.mitama.PoShi;
import test.components.base.BaseTwoEntityTest;

public class PoShiTest extends BaseTwoEntityTest {
    @Override
    public String getLabels() {
        return "御魂 破势";
    }

    @Override
    public void test() {
        first.shiShen = "雨女";
        first.mitama = "破势";
        second.shiShen = "雨女";
        before();
        PoShi poShi = (PoShi) firstEnt.getMitamas().get(0);
        int src;
        src = (int) (secondEnt.getMaxLife() * 1);
        secondEnt.setLife(src);
        simulator.step();
        assert src - secondEnt.getLifeInt() == firstEnt.getComCount() * poShi.getCoefficient() : "破势正1";
        src = (int) (secondEnt.getMaxLife() * 0.701);
        secondEnt.setLife(src);
        simulator.step();
        assert src - secondEnt.getLifeInt() == firstEnt.getComCount() * poShi.getCoefficient() : "破势正2";
        src = (int) (secondEnt.getMaxLife() * 0.7);
        secondEnt.setLife(src);
        simulator.step();
        assert src - secondEnt.getLifeInt() == firstEnt.getComCount() * poShi.getCoefficient() : "破势正3";
        src = (int) (secondEnt.getMaxLife() * 0.7) - 1;
        secondEnt.setLife(src);
        simulator.step();
        assert src - secondEnt.getLifeInt() == firstEnt.getComCount() : "破势负1";
    }
}
