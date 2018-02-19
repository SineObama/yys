package com.sine.yys.simulation.component.model.buff.buff;

import com.sine.yys.simulation.component.model.buff.BaseIBuff;
import com.sine.yys.simulation.component.model.buff.Buff;
import com.sine.yys.simulation.component.model.buff.UniqueIBuff;
import com.sine.yys.simulation.util.CallBack;

/**
 * 辉夜姬身上隐藏的龙首之玉buff，代表龙首之玉幻境。
 *
 * @see com.sine.yys.simulation.component.entity.skill.LongShouZhiYu
 */
public class LongShouZhiYuBuff extends BaseIBuff implements UniqueIBuff, Buff {
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
