package test.components.tests;

import com.sine.yys.shikigami.HuiYeJi;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.LongShouZhiYu;
import com.sine.yys.skill.commonattack.PengLaiYuZhi;
import com.sine.yys.skill.passive.HuoShuQiu;
import test.components.base.BaseThreeEntityTest;

import java.util.Arrays;
import java.util.Collection;

/**
 * 测试火鼠裘群攻下的触发效果。
 */
public class HuoShuQiuTest extends BaseThreeEntityTest {
    private final HuiYeJi huiYeJi = new HuiYeJi() {
        @Override
        protected Collection<BaseSkill> initSkill() {
            return Arrays.asList(new PengLaiYuZhi(), new HuoShuQiu() {
                @Override
                public double getPct() {
                    return 1.0;
                }
            }, new LongShouZhiYu());
        }
    };

    @Override
    protected void init() {
        i1.shikigami = ShikigamiFactory.create("姑获鸟");
        i2.shikigami = huiYeJi;
    }

    @Override
    protected void doTest() {
        int fire = c2.getFire();
        simulator.step();
        assert c2.getFire() == fire + 1 : "+";
    }

    @Override
    public String getLabels() {
        return "技能 火鼠裘";
    }
}
