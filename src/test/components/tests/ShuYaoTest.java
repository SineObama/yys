package test.components.tests;

import com.sine.yys.mitama.ShuYao;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.HuaNiaoXiangWen;
import test.components.base.BaseTwoEntityTest;
import test.components.util.LifeTest;

/**
 * 测试树妖效果。
 */
public class ShuYaoTest extends BaseTwoEntityTest {
    private ShuYao shuYao = new ShuYao();
    private HuaNiaoXiangWen huaNiaoXiangWen;

    @Override
    protected void init() {
        i1.shikigami = ShikigamiFactory.create("花鸟卷");
        i1.mitama = shuYao;
        for (BaseSkill baseSkill : i1.shikigami.getSkills()) {
            if (baseSkill instanceof HuaNiaoXiangWen)
                huaNiaoXiangWen = (HuaNiaoXiangWen) baseSkill;
        }
        if (huaNiaoXiangWen == null)
            throw new RuntimeException("找不到花鸟相闻");
        skillSelector.setaClass(HuaNiaoXiangWen.class);
    }

    @Override
    public void doTest() {
        c1.addFire(3);
        LifeTest test = new LifeTest(e1);
        test.setLife(1);
        simulator.step();
        test.test(e1.getMaxLife() * huaNiaoXiangWen.getDirectLifePct() * (1 + shuYao.getAddCure()), "+");
    }

    @Override
    public String getLabels() {
        return "御魂 树妖";
    }
}
