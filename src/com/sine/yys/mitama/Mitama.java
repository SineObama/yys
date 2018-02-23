package com.sine.yys.mitama;

import com.sine.yys.info.Named;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 御魂。
 */
public interface Mitama extends Named {
    void init(Controller controller, Entity self);
}
