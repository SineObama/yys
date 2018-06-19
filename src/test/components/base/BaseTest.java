package test.components.base;

import com.sine.yys.entity.EntityImpl;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.entity.SimpleObject;
import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.base.Named;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shikigami.BaseShikigami;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import test.components.TestProperty;
import test.components.impl.Handler;
import test.components.inter.SkillSelector;
import test.components.inter.TargetSelector;
import test.components.inter.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 作为基类保有一些变量，定义测试过程（一些抽象函数），进行阵营的装填和模拟初始化等通用逻辑。
 * 也提供一些辅助函数。
 */
public abstract class BaseTest implements Test {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    protected final PVPCamp c1 = new PVPCamp(redName, 0);
    protected final PVPCamp c2 = new PVPCamp(buleName, 0);
    final List<EnInfo> _list1 = new ArrayList<>();
    final List<EnInfo> _list2 = new ArrayList<>();
    final List<SimpleObject> _extra = new ArrayList<>();
    protected Simulator simulator;

    class DispatcherTargetSelector implements TargetSelector {
        final List<EnInfo> list;

        DispatcherTargetSelector(List<EnInfo> list) {
            this.list = list;
        }

        @Override
        public Entity select(Entity self, Camp own, ActiveSkill skill, List<? extends Entity> entities) {
            for (EnInfo enInfo : list)
                if (enInfo.entity == self) {
                    if (enInfo.target == null)
                        return null;
                    return enInfo.target.select(self, own, skill, entities);
                }
            return null;
        }
    }

    class DispatcherSkillSelector implements SkillSelector {
        final List<EnInfo> list;

        DispatcherSkillSelector(List<EnInfo> list) {
            this.list = list;
        }

        @Override
        public ActiveSkill select(Entity self, Camp own, Set<ActiveSkill> skills) {
            for (EnInfo enInfo : list)
                if (enInfo.entity == self) {
                    if (enInfo.skill == null)
                        return null;
                    return enInfo.skill.select(self, own, skills);
                }
            return null;
        }
    }

    private static ShikigamiEntityImpl form(EnInfo i) {
        return new ShikigamiEntityImpl(i.shikigami, i.property, i.mitama, 1.0, i.getName());
    }

    /**
     * @return 普通攻击数值=攻击*普攻系数。
     */
    protected static double com(EntityImpl entity) {
        return entity.getAttack() * entity.getCommonAttack().getCoefficient();
    }

    /**
     * @return 暴击的普通攻击数值=攻击*普攻系数*暴击伤害。
     */
    protected static double comc(EntityImpl entity) {
        return entity.getAttack() * entity.getCommonAttack().getCoefficient() * entity.getCriticalDamage();
    }

    /**
     * 基类进行通用设置。并把式神信息添加进列表。
     */
    abstract void beforeInit();

    /**
     * 自定义具体设置，设置式神、属性、御魂。
     */
    protected abstract void init();

    /**
     * 基类初始化后的设置。通常只是把式神实体引用到本地。
     */
    abstract void afterInit();

    @Override
    public final void test() throws AssertionError {
        Handler handler1 = new Handler();
        handler1.setSkillSelector(new DispatcherSkillSelector(_list1));
        handler1.setTargetSelector(new DispatcherTargetSelector(_list1));
        c1.setHandler(handler1);
        Handler handler2 = new Handler();
        handler2.setSkillSelector(new DispatcherSkillSelector(_list2));
        handler2.setTargetSelector(new DispatcherTargetSelector(_list2));
        c2.setHandler(handler2);
        beforeInit();
        init();
        for (EnInfo info : _list1) {
            info.entity = form(info);
            c1.addEntity(info.entity);
        }
        for (EnInfo info : _list2) {
            info.entity = form(info);
            c2.addEntity(info.entity);
        }
        simulator = new Simulator(c1, c2, _extra);
        simulator.init();
        log.info("初始化模拟完毕");
        afterInit();
        doTest();
    }

    /**
     * 构造战斗环境后被调用，执行测试逻辑。
     */
    protected abstract void doTest();

    protected class EnInfo implements Named {
        public BaseShikigami shikigami = ShikigamiFactory.create("雨女");
        public TestProperty property = new TestProperty();
        public BaseMitama mitama = null;
        public String name = null;
        SkillSelector skill;
        TargetSelector target;
        public ShikigamiEntityImpl entity;  // 初始化后可用

        EnInfo() {
        }

        @Override
        public String getName() {
            return this.name != null ? this.name : this.shikigami.getName();
        }
    }
}
