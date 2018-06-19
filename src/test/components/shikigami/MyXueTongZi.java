package test.components.shikigami;

import com.sine.yys.shikigami.XueTongZi;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.commonattack.XueZou;
import com.sine.yys.skill.mono.LongYueXueHuaZhan;
import com.sine.yys.skill.passive.ShuangTianZhiZhi;

import java.util.Arrays;
import java.util.Collection;

public class MyXueTongZi extends XueTongZi {
    public double pct = 1.0;
    public final ShuangTianZhiZhi shuangTianZhiZhi = new ShuangTianZhiZhi() {
        @Override
        public double getPct() {
            return pct;
        }
    };

    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new XueZou(), shuangTianZhiZhi, new LongYueXueHuaZhan());
    }
}
