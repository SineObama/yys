package test.components.tests;

import com.sine.yys.mitama.ZhaoCaiMao;
import test.components.base.BaseTwoEntityTest;

/**
 * 测试招财猫效果。
 */
public class ZhaoCaiMaoTest extends BaseTwoEntityTest {
    private final ZhaoCaiMao zhaoCaiMao = new ZhaoCaiMao() {
        @Override
        public double getPct() {
            return 1.0;
        }
    };
    private int fire;

    @Override
    protected void init() {
        i1.mitama = zhaoCaiMao;
        fire = c1.getFire();
    }

    @Override
    public void doTest() {
        simulator.step();
        assert c1.getFire() == fire + zhaoCaiMao.getAddFire() : "+";
    }

    @Override
    public String getLabels() {
        return "御魂 招财猫";
    }
}
