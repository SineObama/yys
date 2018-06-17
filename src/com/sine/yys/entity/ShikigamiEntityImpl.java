package com.sine.yys.entity;

import com.sine.yys.buff.control.ControlBuff;
import com.sine.yys.event.*;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.Property;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shikigami.BaseShikigami;
import com.sine.yys.shikigami.operation.OperationImpl;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.util.JSON;
import com.sine.yys.util.Msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 战场中的式神实体（非召唤物）。
 */
public class ShikigamiEntityImpl extends EntityImpl implements ShikigamiEntity {
    private final BaseShikigami shikigami;
    private final List<BaseMitama> mitamas;
    private OperationHandler handler;

    public ShikigamiEntityImpl(BaseShikigami shikigami, Property property, BaseMitama mitama, double lifeTimes, String name) {
        super(property, name, (int) (property.getLife() * lifeTimes));
        this.shikigami = shikigami;
        this.mitamas = new ArrayList<>();
        if (mitama != null)
            this.mitamas.add(mitama);
        this.handler = shikigami.getAI();
    }

    /**
     * @param handler 式神操作的处理器，实现人工控制。null 表示使用原本的AI进行自动控制。
     */
    public void setHandler(OperationHandler handler) {
        if (handler == null)
            this.handler = shikigami.getAI();
        else
            this.handler = handler;
    }

    @Override
    protected final void doInit() {
        super.doInit();
        final Controller controller = getController();
        for (BaseSkill skill : this.shikigami.getSkills())
            skill.init(controller, this, camp);
        for (BaseMitama mitama : this.mitamas)
            mitama.init(controller, this, camp);
    }

    /**
     * 整个行动，包括鬼火处理、技能处理、事件触发、行动后的反击等。
     */
    @Override
    public void action(boolean newRound) {
        // 预备推进鬼火行动条
        this.getFireRepo().ready(newRound);

        call();

        // 完成推进鬼火行动条
        this.getFireRepo().finish(newRound);
    }

    /**
     * 除去鬼火推进的整个行动。
     */
    private void call() {
        if (this.isDead())
            return;
        log.info(Msg.info(this, "行动"));

        for (Skill skill : this.shikigami.getSkills())
            skill.beforeAction();

        // 回合前事件
        // 为了行动前彼岸花的控制效果生效，事件要在buff调用之前。
        this.eventController.trigger(new ZhaoCaiMaoEvent());
        this.eventController.trigger(new BeforeRoundEvent(this));

        getController().afterMovement();

        // 包括执行持续伤害、治疗
        this.buffController.beforeAction(getController());

        if (!this.isDead())
            this.action2();

        // buff回合数-1
        this.buffController.afterAction(getController());

        // 回合后事件
        this.eventController.trigger(new AfterActionEvent(this));
        this.eventController.trigger(new AfterRoundEvent(this));

        for (Skill skill : this.shikigami.getSkills())
            skill.afterAction();

        log.info(Msg.info(this, "行动结束"));
    }

    private void action2() {
        log.info(Msg.info(this, "当前鬼火", this.fireRepo.getFire()));

        final Operation operation;
        // 判断是否有行动控制debuff，进行相关操作。
        final ControlBuff controlBuff = this.buffController.getFirstWithPrior(ControlBuff.class);
        if (controlBuff == null) {  // 无行动控制debuff
            // 获取每个主动技能的可选目标，不添加不可用（无目标），或鬼火不足的技能
            Map<ActiveSkill, List<? extends Entity>> map = new HashMap<>();
            for (ActiveSkill activeSkill : this.getActiveSkills()) {
                int cd = activeSkill.getCD();
                if (cd > 0) {
                    log.info(Msg.info(this, "技能", activeSkill.getName(), "还有CD", cd));
                    continue;
                }
                if (this.fireRepo.getFire() < activeSkill.getFire())
                    continue;
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(this.camp, this);
                if (targets != null)
                    map.put(activeSkill, targets);
            }

            if (!map.isEmpty())
                operation = this.handler.handle(this, this.camp, map);
            else
                operation = new OperationImpl(null, null);

        } else {  // 受行动控制debuff影响

            log.info(Msg.info(this, "受控制效果", controlBuff, "影响"));
            operation = controlBuff.resolve(this, this.shikigami.getAI(), this.camp, this.camp.getOpposite(), this.getCommonAttack());

        }

        // 执行操作
        ActiveSkill activeSkill = operation.getSkill();
        if (activeSkill != null) {
            Entity target = operation.getTarget();
            log.info(Msg.vector(this, target != null ? "对" : "", target, "使用了", activeSkill.getName()));

            // 消耗鬼火
            int fire = activeSkill.getFire();
            if (fire > 0) {
                fire = camp.getEventController().trigger(new UseFireEvent(this, fire)).getCostFire();
                fireRepo.useFire(fire); // XXX 对于荒-月的逻辑修改
                log.info(Msg.info(this, "消耗", fire, "点鬼火，剩余", fireRepo.getFire(), "点"));
            }

            // 执行技能
            activeSkill.apply(target);
            // XXXX 协战的时机，各个普攻各不相同
            if (activeSkill instanceof CommonAttack) {
                // 触发普攻事件
                this.eventController.trigger(new CommonAttackEvent(this, target));
            }

            this.eventController.trigger(new FinishActionEvent());
        } else {
            log.info(Msg.info(this, "无法行动。"));
        }
    }

    @Override
    public final double getAttack() {
        return property.getAttack() + shikigami.getOriginAttack() * buffController.getAtkPct();
    }

    private List<ActiveSkill> getActiveSkills() {
        List<ActiveSkill> activeSkills = new ArrayList<>();
        for (Skill skill : shikigami.getSkills()) {
            if (skill instanceof ActiveSkill) {
                activeSkills.add((ActiveSkill) skill);
            }
        }
        return activeSkills;
    }

    @Override
    public CommonAttack getCommonAttack() {
        for (Skill skill : shikigami.getSkills()) {
            if (skill instanceof CommonAttack)
                return (CommonAttack) skill;
        }
        // XXX 没有普攻技能
        throw new RuntimeException(getFullName() + " 没有普通攻击。");
    }

    @Override
    public List<? extends Mitama> getMitamas() {
        return mitamas;
    }

    public void setFireRepo(FireRepo fireRepo) {
        this.fireRepo = fireRepo;
    }

    @Override
    public String toJSON() {
        return JSON.format("name", getFullName(), "mitama", mitamas, "life", life);
    }
}
