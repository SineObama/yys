package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.info.Closable;
import com.sine.yys.simulation.info.Named;

/**
 * 御魂。
 */
public interface Mitama extends Named, Closable {
    void init(InitContext context);
}
