package com.sine.yys.event;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.ShikigamiEntity;

/**
 * 己方式神受敌方直接单体攻击事件。
 * <p>
 * 用途：
 * 1. 触发薙魂等分担伤害。
 * <p>
 * 触发者：
 * 1. 普攻、反击、单体大招。
 * 2. 天翔鹤斩。
 * <p>
 * 在目标没有椒图连线时，受到直接单体攻击有概率触发一些分担伤害效果，如金鱼、薙魂。
 * 但由于不能多个分担伤害，通过设置 treated 标记实现。
 * 分担当前行动中所守护式神的所有伤害（包括协战）（金鱼不分担针女）。
 * <p>
 * 规则：
 * 1. 对反击生效；
 * 2. 不对召唤物生效，不对草人伤害生效；
 * 3. （似乎）不对己方混乱打己方生效；
 * 4. （似乎）妖刀转火后不会触发；
 * 5. （似乎）不对己方混乱打己方触发的协战生效。
 */
public class BeMonoAttackEvent extends BaseVectorEvent {
    private boolean treated = false;

    public BeMonoAttackEvent(ShikigamiEntity entity, Entity target) {
        super(entity, target);
    }


    public boolean isTreated() {
        return treated;
    }

    public void setTreated() {
        this.treated = true;
    }
}
