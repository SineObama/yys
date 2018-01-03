package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.ContextAndRunner;
import com.sine.yys.simulation.component.SpeedBar;
import com.sine.yys.simulation.component.SpeedBarImpl;
import com.sine.yys.simulation.component.event.UseFire;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.BattleKoinobori;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.entity.Shikigami;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.MingDeng;
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
public class BattleSimulator implements Simulator, ContextAndRunner {
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
    private int costFire;

    public BattleSimulator(final Camp camp0, final Camp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    @Override
    public Camp step() {
        if (!started) {
            started = true;

            Msg.setContext(this);

            // 为技能添加事件回调处理函数
            for (Shikigami shikigami : camp0.getAllShikigami()) {
                for (Skill skill : shikigami.getSkills()) {
                    if (skill instanceof MingDeng) {
                        camp0.getEventController().add(UseFire.class, (MingDeng) skill);
                    }
                }
            }
            for (Shikigami shikigami : camp1.getAllShikigami()) {
                for (Skill skill : shikigami.getSkills()) {
                    if (skill instanceof MingDeng) {
                        camp1.getEventController().add(UseFire.class, (MingDeng) skill);
                    }
                }
            }

            // 加入到行动条中。
            final List<Entity> all = camp0.getAllAlive();
            all.addAll(camp1.getAllAlive());
            speedBar.addAll(all);

            // TODO 战斗开始事件
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

        // 推进鬼火行动条
        own.step();

        round += 1;
        log.info(Msg.turn() + " 序号 " + round);

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
            log.info(Msg.action());

            // 实现操作
            if (operation.getTarget() instanceof Entity)
                target = (Entity) operation.getTarget();
            else
                target = null;
            activeSkill.apply(this);
        }

        // TODO 行动后事件

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
    public void damage(double coefficient) {
        damage(this.target, coefficient);
    }

    @Override
    public void damage(Entity target, double coefficient) {
        boolean critical = RandUtil.success(self.getCritical());
        int damage = (int) CalcDam.expect(self, target, coefficient, critical);
        log.info(Msg.damage(target, damage));

        if (target.getLife() > damage) {
            target.setLife(target.getLife() - damage);
        } else {
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
            log.info(Msg.grabFire(num));
        }
    }

    @Override
    public void useFire(int num) {
        costFire = num;
        own.getEventController().trigger(UseFire.class, this);
        own.useFire(costFire);
    }

    @Override
    public void setCostFire(int num) {
        costFire = num;
    }
}
