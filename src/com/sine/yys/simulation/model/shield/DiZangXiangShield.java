package com.sine.yys.simulation.model.shield;

/**
 * @see com.sine.yys.simulation.model.mitama.DiZangXiang
 */
public class DiZangXiangShield extends BaseShield implements DispellableShield {
    public DiZangXiangShield(int value) {
        super(value, 1);
    }

    @Override
    public String getName() {
        return "地藏像盾";
    }
}
