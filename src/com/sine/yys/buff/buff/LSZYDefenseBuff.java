package com.sine.yys.buff.buff;

import com.sine.yys.buff.UniqueIBuff;

/**
 * 龙首之玉防御buff。
 */
public class LSZYDefenseBuff extends DefenseIBuff implements Buff, UniqueIBuff {
    public LSZYDefenseBuff(double defPct) {
        super(Integer.MAX_VALUE, "龙首之玉-防御", defPct);  // XXXX 另外新建永久buff？
    }
}