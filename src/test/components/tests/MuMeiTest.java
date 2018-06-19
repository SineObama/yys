package test.components.tests;

import com.sine.yys.mitama.MuMei;
import com.sine.yys.shikigami.GuHuoNiao;
import com.sine.yys.skill.groupattack.TianXiangHeZhan;
import test.components.base.BaseThreeEntityTest;

/**
 * 测试木魅的效果，包括单人和多人。
 */
public class MuMeiTest extends BaseThreeEntityTest {
    private double pct = 1.0;
    private final MuMei muMei = new MuMei() {
        @Override
        public double getPct() {
            return pct;
        }
    };

    @Override
    protected void init() {
        i1.shikigami = new GuHuoNiao();
        i2.mitama = muMei;
    }

    @Override
    protected void doTest() {
        c1.addFire(8);
        int fire = c1.getFire();
        simulator.step();
        fire -= 1;
        assert c1.getFire() == fire : "-1 fire";

        skillSelector.setaClass(TianXiangHeZhan.class);
        simulator.step();
        fire -= new TianXiangHeZhan().getFire() + 2;
        assert c1.getFire() == fire : "-2 fire";
    }

    @Override
    public String getLabels() {
        return "御魂 木魅";
    }
}
