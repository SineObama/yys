package test.components.tests;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.mitama.XueYouHun;
import test.components.base.BaseTwoEntityTest;
import test.components.shikigami.MyXueTongZi;
import test.components.util.LifeTest;

/**
 * 测试霜天之织与雪幽魂的叠加效果。
 */
public class ShuangTianZhiZhiXueYouHunTest extends BaseTwoEntityTest {
    private MyXueTongZi xueTongZi = new MyXueTongZi();
    private double pct = 0.0;
    private XueYouHun xueYouHun = new XueYouHun() {
        @Override
        public double getPct() {
            return pct;
        }
    };
    private String prefix;

    @Override
    protected void init() {
        i1.shikigami = xueTongZi;
        i1.mitama = xueYouHun;
        xueTongZi.pct = 1.0;
    }

    @Override
    protected void doTest() {
        final double c = xueTongZi.shuangTianZhiZhi.getBreakCoefficient();

        LifeTest test = new LifeTest(e2);

        Callback callback = () -> {
            test.setLife(e2.getMaxLife());
            simulator.step();
            test.test(-com(e1), prefix + "伤害-");
            assert e2.getBuffController().contain(BingDong.class) : prefix + "冰冻1";

            simulator.step();
            test.test(-com(e1) * c, prefix + "伤害+");
            assert !e2.getBuffController().contain(BingDong.class) : prefix + "碎冰1";
        };
        prefix = "霜天之织冰冻-";
        callback.call();
        pct = 1.0;
        xueTongZi.pct = 0.0;
        prefix = "雪幽魂冰冻-";
        callback.call();
    }

    @Override
    public String getLabels() {
        return "技能 霜天之织 御魂 雪幽魂";
    }

}
