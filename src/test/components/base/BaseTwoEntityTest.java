package test.components.base;

import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.inter.CommonAttack;
import test.components.impl.SkillTypeSelector;
import test.components.impl.TargetNameSelector;

/**
 * 两个式神对打的测试，只有一方出手。
 */
public abstract class BaseTwoEntityTest extends BaseTest implements Test {
    protected SkillTypeSelector skillSelector;
    protected TargetNameSelector targetSelector;
    protected final EnInfo i1 = new EnInfo();
    protected final EnInfo i2 = new EnInfo();
    protected ShikigamiEntityImpl e1;
    protected ShikigamiEntityImpl e2;

    @Override
    void beforeInit() {
        i1.skill = skillSelector = new SkillTypeSelector(CommonAttack.class);
        i1.target = targetSelector = new TargetNameSelector(i2);
        i1.property.speed = 10000;
        i2.property.speed = 1;
        _list1.add(i1);
        _list2.add(i2);
    }

    @Override
    void afterInit() {
        e1 = i1.entity;
        e2 = i2.entity;
    }
}
