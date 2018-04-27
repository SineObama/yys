package test.components.base;

import com.sine.yys.entity.ShikigamiEntityImpl;

/**
 * 1打2的测试。
 */
public abstract class BaseThreeEntityTest extends BaseTwoEntityTest implements Test {
    protected final EnInfo i3 = new EnInfo();
    protected ShikigamiEntityImpl e3;

    @Override
    void beforeInit() {
        super.beforeInit();
        i3.property.speed = 1;
        i3.name = "旁观";
    }

    @Override
    void afterInit() {
        super.afterInit();
        e3 = form(i3);
        _list2.add(e3);
    }
}
