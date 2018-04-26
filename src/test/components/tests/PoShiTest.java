package test.components.tests;

import com.sine.yys.mitama.PoShi;
import test.components.base.BaseTwoEntityTest;

public class PoShiTest extends BaseTwoEntityTest {
    private PoShi poShi = new PoShi();

    @Override
    protected void init() {
        i1.mitama = poShi;
    }

    @Override
    public void doTest() {
        int src;
        src = (int) (e2.getMaxLife() * 1);
        e2.setLife(src);
        simulator.step();
        assert src - e2.getLifeInt() == e1.getComCount() * poShi.getCoefficient() : "破势正1";
        src = (int) (e2.getMaxLife() * 0.701);
        e2.setLife(src);
        simulator.step();
        assert src - e2.getLifeInt() == e1.getComCount() * poShi.getCoefficient() : "破势正2";
        src = (int) (e2.getMaxLife() * 0.7);
        e2.setLife(src);
        simulator.step();
        assert src - e2.getLifeInt() == e1.getComCount() * poShi.getCoefficient() : "破势正3";
        src = (int) (e2.getMaxLife() * 0.7) - 1;
        e2.setLife(src);
        simulator.step();
        assert src - e2.getLifeInt() == e1.getComCount() : "破势负1";
    }

    @Override
    public String getLabels() {
        return "御魂 破势";
    }
}
