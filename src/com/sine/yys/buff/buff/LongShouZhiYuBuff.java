package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Buff;
import com.sine.yys.inter.CallBack;
import com.sine.yys.inter.Entity;

/**
 * 辉夜姬身上隐藏的龙首之玉buff，代表龙首之玉幻境。
 *
 * @see com.sine.yys.skill.LongShouZhiYu
 */
public class LongShouZhiYuBuff extends BaseCommonIBuff implements Buff {
    private final CallBack callBack;

    public LongShouZhiYuBuff(int last, CallBack callBack, Entity src) {
        super(last, "龙首之玉", src);
        this.callBack = callBack;
    }

    @Override
    public void onRemove() {
        callBack.call();
    }
}
