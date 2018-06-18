package test.components.tests;

import com.sine.yys.buff.debuff.PctDoT;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;
import com.sine.yys.shikigami.JiaoTu;
import com.sine.yys.shikigami.LianYou;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.XiongDiZhiBan;
import com.sine.yys.skill.commonattack.PangZou;
import com.sine.yys.skill.passive.RenDuoShiZhong;
import com.sine.yys.skill.passive.RunWuWuSheng;
import test.components.base.BaseTwoEntityTest;

import java.util.Arrays;
import java.util.Collection;

/**
 * 测试buff伤害（镰鼬）对润物无声的触发效果。
 */
public class RunWuWuShengBuffTest extends BaseTwoEntityTest {
    private final LianYou lianYou = new LianYou() {
        @Override
        protected Collection<BaseSkill> initSkill() {
            return Arrays.asList(new PangZou() {
                @Override
                public double getPct() {
                    return 1.0;
                }

                @Override
                public IBuff getDebuff(Entity self) {
                    return new PctDoT(getLast(), getName(), getReduceLifePct(), self) {
                    };
                }
            }, new RenDuoShiZhong(), new XiongDiZhiBan());
        }
    };

    @Override
    protected void init() {
        i1.shikigami = lianYou;
        i2.shikigami = new JiaoTu();
        i2.property.speed = i1.property.speed - 1;
    }

    @Override
    public void doTest() {
        simulator.step();
        simulator.step();
        assert e2.getBuffController().get(RunWuWuSheng.Buff.class).getLast() > new RunWuWuSheng().getLast() : "+";
    }

    @Override
    public String getLabels() {
        return "技能 润物无声";
    }
}
