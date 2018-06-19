package test.components.tests;

import com.sine.yys.inter.CommonAttack;
import com.sine.yys.shikigami.LianYou;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.XiongDiZhiBan;
import com.sine.yys.skill.commonattack.PangZou;
import com.sine.yys.skill.passive.RenDuoShiZhong;
import test.components.base.BaseTwoEntityTest;

import java.util.Arrays;
import java.util.Collection;

/**
 * 测试人多势众的效果。
 * * 可以获得额外行动；
 * * 拉条后行动条归0；
 */
public class RenDuoShiZhongTest extends BaseTwoEntityTest {
    private double pct = 1.0;
    private XiongDiZhiBan xiongDiZhiBan = new XiongDiZhiBan();
    private final LianYou lianYou = new LianYou() {
        @Override
        protected Collection<BaseSkill> initSkill() {
            return Arrays.asList(new PangZou(), new RenDuoShiZhong() {
                @Override
                public double getPct() {
                    return pct;
                }
            }, xiongDiZhiBan);
        }
    };

    @Override
    protected void init() {
        i1.shikigami = lianYou;
        skillSelector.setaClass(XiongDiZhiBan.class);
    }

    @Override
    public void doTest() {
        c1.addFire(6);
        simulator.step();
        assert e1.getPosition() == 0.0 : "行动条归0+";
        double pos = e2.getPosition();
        pct = 0.0;

        simulator.step();
        assert e1.getPosition() == xiongDiZhiBan.getAddPosition() : "行动条归0-";
        assert e2.getPosition() == pos : "额外行动+";

        skillSelector.setaClass(CommonAttack.class);
        simulator.step();
        assert e2.getPosition() > pos : "额外行动-";
    }

    @Override
    public String getLabels() {
        return "技能 人多势众 兄弟之绊";
    }
}
