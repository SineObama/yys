package com.sine.yys.simulation.model.shield;

/**
 * @see com.sine.yys.simulation.model.mitama.BangJing
 */
public class BangJingShield extends BaseShield {
    public BangJingShield(int value) {
        super(value, 1);
    }

    @Override
    public String getName() {
        return "蚌精盾";
    }
}
