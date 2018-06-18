package test.components.tests;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.shikigami.JiaoTu;
import com.sine.yys.skill.JuanLiu;
import test.components.base.BaseThreeEntityTest;
import test.components.impl.MyXueTongZi;
import test.components.util.LifeTest;

/**
 * 测试霜天之织的效果，包括通过涓流传递。
 */
public class ShuangTianZhiZhiTest extends BaseThreeEntityTest {
    private MyXueTongZi xueTongZi = new MyXueTongZi();

    @Override
    protected void init() {
        i1.shikigami = xueTongZi;
        xueTongZi.pct = 1.0;
        i2.shikigami = new JiaoTu();
        i2.property.speed = i1.property.speed / 2 - 1;
    }

    @Override
    protected void doTest() {
        final double c = xueTongZi.shuangTianZhiZhi.getBreakCoefficient();
        final double juanLiu = 1 - new JuanLiu().getReduceDamage();

        LifeTest test = new LifeTest(e2);

        simulator.step();
        test.test(-com(e1), "伤害-");
        assert e2.getBuffController().contain(BingDong.class) : "冰冻1";

        simulator.step();
        test.test(-com(e1) * c, "伤害+");
        assert !e2.getBuffController().contain(BingDong.class) : "碎冰1";

        c2.addFire(3);
        simulator.step(); // 涓流
        assert !e2.getBuffController().contain(BingDong.class) : "无冰冻";

        LifeTest test3 = new LifeTest(e3);

        test.setLife(e2.getMaxLife());
        test3.setLife(e3.getMaxLife());
        simulator.step();
        test.test(-com(e1) / 2 * juanLiu, "涓流伤害-1");
        test3.test(-com(e1) / 2 * juanLiu, "涓流伤害-2");
        assert e2.getBuffController().contain(BingDong.class) : "冰冻2";
        assert e3.getBuffController().contain(BingDong.class) : "涓流冰冻";

        test.setLife(e2.getMaxLife());
        test3.setLife(e3.getMaxLife());
        simulator.step();
        test.test(-com(e1) / 2 * juanLiu * c, "涓流伤害+2");
        test3.test(-com(e1) / 2 * juanLiu * c * c, "涓流伤害+2");
        assert !e2.getBuffController().contain(BingDong.class) : "碎冰2";
        assert !e3.getBuffController().contain(BingDong.class) : "涓流碎冰";
    }

    @Override
    public String getLabels() {
        return "技能 霜天之织";
    }

}
