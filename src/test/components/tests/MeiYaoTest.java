package test.components.tests;

import com.sine.yys.buff.control.HunLuan;
import com.sine.yys.mitama.MeiYao;
import test.components.base.BaseTwoEntityTest;

/**
 * 测试魅妖的触发效果。
 */
public class MeiYaoTest extends BaseTwoEntityTest {
    private MeiYao meiYao = new MeiYao() {
        @Override
        public double getPct() {
            return 1.0;
        }
    };

    @Override
    protected void init() {
        i1.mitama = meiYao;
    }

    @Override
    public void doTest() {
        simulator.step();
        assert e2.getBuffController().contain(HunLuan.class) : "+";
    }

    @Override
    public String getLabels() {
        return "御魂 魅妖";
    }
}
