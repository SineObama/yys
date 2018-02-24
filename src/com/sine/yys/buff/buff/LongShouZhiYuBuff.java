package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.util.CallBack;

/**
 * 辉夜姬身上隐藏的龙首之玉buff，代表龙首之玉幻境。
 *
 * @see com.sine.yys.skill.LongShouZhiYu
 */
public class LongShouZhiYuBuff extends BaseIBuff implements Buff {
    private final CallBack callBack;

    public LongShouZhiYuBuff(int last, CallBack callBack) {
        super(last, "龙首之玉幻境");
        this.callBack = callBack;
    }

    @Override
    protected void afterStep() {
        if (getLast() == 0)
            callBack.call();
    }
}
