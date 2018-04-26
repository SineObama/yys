package test.components.base;

import com.sine.yys.entity.EntityImpl;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.entity.SimpleObject;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shikigami.BaseShikigami;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import test.components.TestProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 作为基类保有一些变量，提供初始化逻辑。
 */
public abstract class BaseTest implements Test {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    protected final PVPCamp c1 = new PVPCamp(redName, 0);
    protected final PVPCamp c2 = new PVPCamp(buleName, 0);
    protected final List<ShikigamiEntityImpl> _list1 = new ArrayList<>();
    protected final List<ShikigamiEntityImpl> _list2 = new ArrayList<>();
    protected final List<SimpleObject> _extra = new ArrayList<>();
    protected Simulator simulator;

    protected static ShikigamiEntityImpl form(EnInfo i) {
        return new ShikigamiEntityImpl(i.shikigami, i.property, i.mitama, 1.0, i.name != null ? i.name : i.shikigami.getName());
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

    final void initSimulation() {
        for (ShikigamiEntityImpl entity : _list1)
            c1.addEntity(entity);
        for (ShikigamiEntityImpl entity : _list2)
            c2.addEntity(entity);
        simulator = new Simulator(c1, c2, _extra);
        simulator.init();
        log.info("初始化模拟完毕");
    }

    protected class EnInfo {
        public BaseShikigami shikigami = ShikigamiFactory.create("雨女");
        public TestProperty property = new TestProperty();
        public BaseMitama mitama = null;
        public String name = null;
    }

    /**
     * 辅助生命值检查。
     */
    protected class LifeTest {
        int life;
        int shield = 0;
        ShikigamiEntityImpl entity;

        public LifeTest(ShikigamiEntityImpl entity) {
            this.entity = entity;
            this.life = entity.getMaxLife();
        }

        public void addShield(double count) {
            shield += count;
        }

        public void setShield(int shield) {
            this.shield = shield;
        }

        public int getLife() {
            return life;
        }

        public void setLife(double life) {
            this.life = (int) life;
            entity.setLife((int) life);
        }

        public void test(double change, String message) {
            if (change < 0) {
                shield += change;
                if (shield < 0) {
                    change = shield;
                    shield = 0;
                } else {
                    change = 0;
                }
            }
            life += change;
            assert life == entity.getLifeInt() : message;
        }
    }
}
