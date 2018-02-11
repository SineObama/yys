package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.SpeedBar;
import com.sine.yys.simulation.component.SpeedBarImpl;
import com.sine.yys.simulation.component.event.*;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.Attack;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.BattleKoinobori;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.Skill;
import com.sine.yys.simulation.rule.CalcDam;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 红白竞猜模拟器？
 */
public class BattleSimulator implements Simulator, Controller {
    private final Logger log = Logger.getLogger(this.getClass().toString());

    // 引用
    private final Camp camp0, camp1;
    private final BattleKoinobori battleKoinobori = new BattleKoinobori(100.0);  // 战场鲤鱼旗
    private final SpeedBar speedBar = new SpeedBarImpl();

    // 设置
    private boolean auto = true;
    private OperationHandler handler = null;

    // 状态
    private boolean started = false;
    private int round = 0;

    // 当前行动状态
    private Camp own, enemy;
    private Entity self, target;
    private ActiveSkill activeSkill;

    public BattleSimulator(final Camp camp0, final Camp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    @Override
    public Camp step() {
        if (!started) {
            started = true;

            // 初始化，为技能添加事件回调处理函数
            InitContext context = new InitContext();
            context.setOwn(camp0);
            context.setEnemy(camp1);
            camp0.init(context);
            context.setOwn(camp1);
            context.setEnemy(camp0);
            camp1.init(context);

            // 加入到行动条中。
            speedBar.addAll(camp0.getAllAlive());
            speedBar.addAll(camp1.getAllAlive());

            BattleStartEvent startEvent = new BattleStartEvent();
            camp0.getEventController().trigger(startEvent, this);
            camp1.getEventController().trigger(startEvent, this);
        }

        // 获取下一行动式神
        self = speedBar.step();

        // 确定敌我阵营
        if (camp0.contain(self)) {
            own = camp0;
            enemy = camp1;
        } else if (camp1.contain(self)) {
            own = camp1;
            enemy = camp0;
        } else {
            // 战场鲤鱼旗行动
            return null;
        }

        round += 1;
        log.info(Msg.info(self, "行动") + " 序号 " + round);

        // 推进鬼火行动条
        own.step();

        // TODO 行动前事件

        // 获取每个主动技能的可选目标
        Map<ActiveSkill, List<? extends Target>> map = new HashMap<>();
        for (Skill skill : self.getSkills()) {
            if (skill instanceof ActiveSkill) {
                ActiveSkill activeSkill = (ActiveSkill) skill;
                final List<? extends Target> targets = activeSkill.getTargetResolver().resolve(self, own, enemy);
                if (!targets.isEmpty())
                    map.put(activeSkill, targets);
            }
        }

        if (!map.isEmpty()) {
            // 获取操作，手动或自动AI选择。
            final Operation operation;
            if (auto)
                operation = self.getAI().handle(own, map);
            else
                operation = handler.handle(own, map);
            activeSkill = operation.getSkill();
            log.info(Msg.action(self, activeSkill));

            // 实现操作
            if (operation.getTarget() instanceof Entity)
                target = (Entity) operation.getTarget();
            else
                target = null;
            activeSkill.apply(this);
        }

        // TODO 行动后事件
        self.getBuffController().afterAction(self);

        // 简单判断胜负：无式神存活
        if (own.getAllShikigami().size() == 0)
            return enemy;
        if (enemy.getAllShikigami().size() == 0)
            return own;
        return null;
    }

    @Override
    public Camp getCamp0() {
        return camp0;
    }

    @Override
    public Camp getCamp1() {
        return camp1;
    }

    @Override
    public BattleKoinobori getBattleKoinobori() {
        return battleKoinobori;
    }

    @Override
    public boolean isAuto() {
        return auto;
    }

    @Override
    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    @Override
    public OperationHandler getHandler() {
        return handler;
    }

    @Override
    public void setHandler(OperationHandler handler) {
        this.handler = handler;
    }

    @Override
    public SpeedBar getSpeedBar() {
        return speedBar;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public Camp getOwn() {
        return own;
    }

    @Override
    public Camp getEnemy() {
        return enemy;
    }

    @Override
    public Entity getSelf() {
        return self;
    }

    @Override
    public Entity getTarget() {
        return this.target;
    }

    @Override
    public ActiveSkill getActiveSkill() {
        return activeSkill;
    }

    @Override
    public void damage(Attack attack) {
        damage(this.target, attack);
    }

    @Override
    public void damage(Entity target, Attack attack) {
        damage(this.self, target, attack);
    }

    @Override
    public void damageFrom(Entity self, Attack attack) {
        damage(self, this.target, attack);
    }

    /**
     * 伤害逻辑：
     * 1. 由攻击、伤害系数、对方防御（忽略防御）计算。
     * 2. 根据双方buff进行增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，添加御魂效果。
     */
    @Override
    public void damage(Entity self, Entity target, Attack attack) {
        if (!target.isAlive())  // TODO 更好的逻辑？
            return;

        // 1.
        final boolean critical = RandUtil.success(self.getCritical());
        if (critical)
            log.info(Msg.info(self, "暴击"));
        double damage = CalcDam.expect(self, target, attack, critical);

        //3.
        int remain = breakShield(target, (int) damage);

        // 4.
        if (remain != 0) {
            damage = remain;

            PreDamageEvent event = new PreDamageEvent();
            event.setTarget(target);
            self.getEventController().trigger(event, this);
            damage *= event.getCoefficient();

            doDamage(self, target, damage);

            if (critical) {
                target.getEventController().trigger(new BeCriticalEvent(target, self), this);
                self.getEventController().trigger(new CriticalEvent(self, target), this);
            }
        } else {
            log.info(Msg.noDamage(self, target));
        }
    }

    /**
     * 目前只有针女伤害。
     * 1. 计算伤害。
     * 2. 根据旗帜buff增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，附加效果（似乎有比如山童的眩晕）。
     */
    @Override
    public void realDamage(Entity self, Entity target, double maxByAttack, double maxPctByMaxLife) {
        if (!target.isAlive())
            return;
        // 1.
        final double damage1 = self.getAttack() * maxByAttack;
        final double damage2 = target.getMaxLife() * maxPctByMaxLife;
        double damage = damage1 < damage2 ? damage1 : damage2;

        // 3.
        int remain = breakShield(target, (int) damage);

        // 4.
        if (remain != 0) {
            doDamage(self, target, damage);
        } else {
            log.info(Msg.noDamage(self, target));
        }

    }

    /**
     * 把伤害施加到盾上。
     *
     * @return 剩余伤害。
     */
    private int breakShield(Entity target, int damage) {
        for (Shield shield : target.getBuffController().getShields()) {
            damage = shield.doDamage(damage);
            if (damage == 0)
                break;
        }
        return damage;
    }

    private void doDamage(Entity self, Entity target, double damage) {
        log.info(Msg.damage(self, target, (int) damage));
        if (target.getLife() > damage) {
            target.setLife(target.getLife() - (int) damage);
        } else {
            log.info(Msg.kill(self, target));
            target.setLife(0);
            enemy.getPosition(target).setDead(true);
        }
    }

    @Override
    public void randomGrab(double pct, int times) {
        int num = RandUtil.count(pct, times);
        if (num > 0) {
            num = enemy.grabFire(num);
            own.addFire(num);
            log.info(Msg.grabFire(own, enemy, num));
        }
    }

    @Override
    public void useFire(int num) {
        UseFireEvent event = new UseFireEvent(this.self, num);
        own.getEventController().trigger(event, this);
        own.useFire(event.getCostFire());
        log.info(Msg.useFire(self, event.getCostFire()));
    }

    @Override
    public void setTarget(Entity entity) {
        target = entity;
    }
}
