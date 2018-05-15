package com.sine.yys.inter;

import java.util.List;

/**
 * 战场中的式神（非召唤物）实体。
 */
public interface ShikigamiEntity extends Entity {
    List<? extends Mitama> getMitamas();
}
