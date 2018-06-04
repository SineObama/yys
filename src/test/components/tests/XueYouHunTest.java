package test.components.tests;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.mitama.XueYouHun;
import test.components.base.BaseTwoEntityTest;

/**
 * 测试雪幽魂的触发效果。
 */
public class XueYouHunTest extends BaseTwoEntityTest {
    private XueYouHun xueYouHun = new XueYouHun() {
        @Override
        public double getPct() {
            return 1.0;
        }
    };

    @Override
    protected void init() {
        i1.mitama = xueYouHun;
    }

    @Override
    public void doTest() {
        simulator.step();
        assert e2.getBuffController().contain(BingDong.class) : "+";
    }

    @Override
    public String getLabels() {
        return "御魂 雪幽魂";
    }
}
