package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.base.Callback;

/**
 * 辉夜姬身上隐藏的龙首之玉buff，代表龙首之玉幻境。
 *
 * @see com.sine.yys.skill.LongShouZhiYu
 */
public class LongShouZhiYuBuff extends BaseCommonIBuff {
    private final Callback callback;

    public LongShouZhiYuBuff(int last, Callback callback, Entity src) {
        super(last, "龙首之玉", src);
        this.callback = callback;
    }

    @Override
    public void onRemove(Entity self) {
        callback.call();
    }
}
