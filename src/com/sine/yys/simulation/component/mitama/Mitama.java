package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.info.Named;
import com.sine.yys.simulation.info.Sealable;

/**
 * 御魂。
 */
public interface Mitama extends Named, Sealable {
    void init(InitContext context);
}
