package com.sine.yys.effect;

import com.sine.yys.inter.base.Named;

import java.util.logging.Logger;

/**
 * 效果（定义不太明确）。
 * 御魂或技能上某种效果，是相对独立的，可用作{@linkplain com.sine.yys.inter.EventHandler}。
 */
public abstract class BaseEffect implements Named {
    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private final String name;

    BaseEffect(String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }
}
