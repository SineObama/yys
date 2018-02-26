package com.sine.yys.buff.buff;

import com.sine.yys.inter.Buff;
import com.sine.yys.inter.Entity;

/**
 * 龙首之玉防御buff。
 */
public class LSZYDefenseBuff extends DefenseIBuff implements Buff {
    public LSZYDefenseBuff(double defPct, Entity src) {
        super(Integer.MAX_VALUE, "龙首之玉-防御", defPct, src);  // XXXX 另外新建永久buff？
    }
}
