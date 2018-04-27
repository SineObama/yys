package test.components.base;

import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.inter.CommonAttack;
import test.components.impl.Handler;
import test.components.impl.SkillTypeSelector;
import test.components.impl.TargetNameSelector;

/**
 * 两个式神对打的测试，只有一方出手。
 */
public abstract class BaseTwoEntityTest extends BaseTest implements Test {
    protected final Handler handler = new Handler();
    protected final EnInfo i1 = new EnInfo();
    protected final EnInfo i2 = new EnInfo();
    protected final TargetNameSelector targetSelector = new TargetNameSelector(i2.shikigami.getName());
    protected final SkillTypeSelector skillSelector = new SkillTypeSelector(CommonAttack.class);
    protected ShikigamiEntityImpl e1;
    protected ShikigamiEntityImpl e2;

    @Override
    void beforeInit() {
        handler.setSkillSelector(skillSelector);
        handler.setTargetSelector(targetSelector);
        c1.setHandler(handler);
        i1.property.speed = 10000;
        i2.property.speed = 1;
    }

    @Override
    void afterInit() {
        e1 = form(i1);
        _list1.add(e1);
        e2 = form(i2);
        _list2.add(e2);
    }
}
