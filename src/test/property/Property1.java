package test.property;

import com.sine.yys.impl.PropertyImpl;

/**
 * 无防御暴击命中抵抗。伤害直接就是攻击。
 */
public class Property1 extends PropertyImpl {
    public Property1(double speed) {
        super(1000, 10000, 0, speed, 0, 0, 0, 0);
    }
}
