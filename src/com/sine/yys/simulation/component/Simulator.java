package com.sine.yys.simulation.component;

import com.sine.yys.buff.debuff.ControlBuff;
import com.sine.yys.buff.debuff.HunLuan;
import com.sine.yys.event.*;
import com.sine.yys.inter.*;
import com.sine.yys.skill.operation.OperationImpl;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 战场模拟器。安排战斗主干流程。
 */
public class Simulator {
    private final Logger log = Logger.getLogger(getClass().getName());

    // 引用
    private final BaseCamp camp0, camp1;
    private final List<EntityImpl> extras;  // 额外的对象，包括不属于阵营的战场鲤鱼旗。秘闻竞赛副本的鬼头？
    private ControllerImpl controller;
    private Camp win = null;
    // 状态
    private boolean started = false;
    private boolean ended = false;
    private int round = 0;

    public Simulator(final BaseCamp camp0, final BaseCamp camp1, List<EntityImpl> extras) {
        this.camp0 = camp0;
        this.camp1 = camp1;
        this.extras = extras;
        controller = new ControllerImpl(camp0, camp1);
    }

    /**
     * 双方阵营的初始化。因为对称，提取为函数。
     * <p>
     * 阵营和式神只存储关系和状态（事件、buff），主体逻辑（施加伤害和效果，事件监听等）放在技能和御魂中。
     * <p>
     * 式神的所属阵营和鬼火仓库交由阵营来设置（初始化），这里另外负责把{@link Controller}注入到技能和御魂中。
     *
     * @param own   己方。
     * @param enemy 敌方。
     */
    private void init(BaseCamp own, BaseCamp enemy) {
        own.init(enemy);
        for (EntityImpl baseEntity : own.getAllAlive()) {
            for (Skill skill : baseEntity.shikigami.getSkills()) {
                skill.init(controller, baseEntity);
            }
            for (Mitama mitama : baseEntity.mitamas) {
                mitama.init(controller, baseEntity);
            }
        }
    }

    private EntityImpl next() {
        EntityImpl rtn = null;
        double min = 1;  // 不可能达到的较大值
        List<EntityImpl> all = new ArrayList<>(15);
        all.addAll(camp0.getAllAlive());
        all.addAll(camp1.getAllAlive());
        all.addAll(extras);
        for (EntityImpl entity : all) {
            double remain = (1 - entity.getPosition()) / entity.getSpeed();
            if (min > remain) {
                min = remain;
                rtn = entity;
            }
        }
        for (EntityImpl entity : all) {
            entity.setPosition(entity.getPosition() + min * entity.getSpeed());
        }
        rtn.setPosition(1.0);
        return rtn;
    }

    /**
     * 初始化。
     */
    private void init() {
        started = true;
        init(camp0, camp1);
        init(camp1, camp0);
        camp0.getEventController().trigger(new BattleStartEvent(controller));
        camp1.getEventController().trigger(new BattleStartEvent(controller));
    }

    public void step() {
        if (ended)
            return;
        if (!started)
            init();

        // 获取下一行动式神
        EntityImpl self = next();

        // TODO 战场鲤鱼旗行动
        if (self.getCamp() == null) {
            self.setPosition(0);
            return;
        }

        action(self);
    }

    private boolean checkWin() {
        // XXX 简单判断胜负：无式神存活
        if (camp0.getAllShikigami().size() == 0) {
            win = camp1;
            return ended = true;
        }
        if (camp1.getAllShikigami().size() == 0) {
            win = camp0;
            return ended = true;
        }
        return false;
    }

    /**
     * 整个行动，包括鬼火处理、技能处理、事件触发、行动后的反击等。
     * 多次行动不会返回。
     */
    private void action(EntityImpl self) {

        // 预备推进鬼火行动条
        self.fireRepo.ready();

        // 用于多次行动
        do {
            round += 1;
            log.info(Msg.info(self, "行动，序号 " + round));

            // 重置行动条
            self.setPosition(0);


            for (Skill skill : self.shikigami.getSkills())
                skill.beforeAction(controller, self);

            // 行动前事件
            // 为了行动前彼岸花的控制效果生效，事件要在buff调用之前。
            self.camp.getEventController().trigger(new BeforeActionEvent(controller, self));
            self.eventController.trigger(new BeforeActionEvent(controller, self));

            controller.afterMovement();

            // XXXX 行动前事件死了的影响
            // 包括执行持续伤害
            self.buffController.beforeAction(controller, self);

            if (!self.isDead())
                doAction(self);

            controller.afterMovement();

            // 一般buff回合数-1
            self.buffController.afterAction(controller, self);

            // 行动后事件
            self.camp.getEventController().trigger(new AfterActionEvent(controller, self));
            self.eventController.trigger(new AfterActionEvent(controller, self));

            for (Skill skill : self.shikigami.getSkills())
                skill.afterAction(controller, self);

            // 完成推进鬼火行动条
            self.fireRepo.finish();

            // TODO 行动后行为，反击等。记得调用controller.afterMovement();
            // FIXME 触发新回合后被反击打死，行动条还在1

            log.info(Msg.info(self, "行动结束，序号 " + round));
            if (checkWin())
                break;
        } while (self.getPosition() == 1.0 && !self.isDead());
    }

    /**
     * 式神自身的行动逻辑。
     */
    private void doAction(EntityImpl self) {
        final Operation operation;
        // 判断是否有行动控制debuff，进行相关操作。
        final List<ControlBuff> controlBuffs = self.buffController.getControlBuffs();
        if (controlBuffs.isEmpty()) {  // 无行动控制debuff
            // 获取每个主动技能的可选目标，不添加不可用（无目标），或鬼火不足的技能
            Map<ActiveSkill, List<? extends Entity>> map = new HashMap<>();
            for (ActiveSkill activeSkill : self.getActiveSkills()) {
                int cd = activeSkill.getCD(self);
                if (cd > 0) {
                    log.info(Msg.info(self, "技能 " + activeSkill.getName() + " 还有CD " + cd));
                    continue;
                }
                if (self.fireRepo.getFire() < activeSkill.getFire())
                    continue;
                final List<? extends Entity> targets = activeSkill.getTargetResolver().resolve(self.getCamp(), self);
                if (targets != null)
                    map.put(activeSkill, targets);
            }

            if (!map.isEmpty())
                operation = self.shikigami.getAI().handle(self, map);
            else
                operation = new OperationImpl(null, null);

        } else {  // 受行动控制debuff影响

            ControlBuff controlBuff = controlBuffs.get(0);
            log.info(Msg.info(self, "受行动控制debuff " + controlBuff.getName() + " 影响"));
            if (controlBuff instanceof HunLuan) {  // 混乱，使用普通攻击，随机攻击一个目标
                final List<Entity> allAlive = new ArrayList<>();
                allAlive.addAll(self.camp.getAllAlive());
                allAlive.addAll(self.camp.getOpposite().getAllAlive());
                allAlive.remove(self);
                operation = new OperationImpl(RandUtil.choose(allAlive), self.getCommonAttack());
            } else {
                operation = new OperationImpl(null, null);
            }

        }

        // 执行操作
        ActiveSkill activeSkill = operation.getSkill();
        if (activeSkill != null) {
            Entity target = operation.getTarget();
            log.info(Msg.info(self, "当前鬼火 " + self.fireRepo.getFire()));
            log.info(Msg.vector(self, target != null ? "对" : "", target, "使用了 " + activeSkill.getName()));

            // 消耗鬼火
            int fire = activeSkill.getFire();
            if (fire > 0) {
                UseFireEvent event = new UseFireEvent(controller, self, fire);
                self.camp.getEventController().trigger(event);
                fire = event.getCostFire();
                self.fireRepo.useFire(fire); // XXX 对于荒-月的逻辑修改
                log.info(Msg.info(self, "消耗 " + fire + " 点鬼火，剩余 " + self.fireRepo.getFire() + " 点"));
            }

            // 执行技能
            activeSkill.apply(controller, self, target);

            self.eventController.trigger(new FinishActionEvent(controller));
        } else {
            log.info(Msg.info(self, "无法行动。"));
        }
    }

    public Camp getWin() {
        return win;
    }

    public int getRound() {
        return round;
    }
}
