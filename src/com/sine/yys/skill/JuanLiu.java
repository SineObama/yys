package com.sine.yys.skill;

import com.sine.yys.buff.NumIBuff;
import com.sine.yys.event.BeforeActionEvent;
import com.sine.yys.event.DamageShareEvent;
import com.sine.yys.event.DieEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.Self;

import java.util.List;

/**
 * 椒图-涓流。
 * <p>
 * 规则：
 * 1. 回合开始时结束，断开全体链接（在赤团华前）；
 * 2. 死亡时断开全体链接；
 * 3. 荒川可切断其他式神的链接，不能切断椒图自身的。
 */
public class JuanLiu extends BaseNoTargetSkill implements EventHandler<DamageShareEvent> {
    private final EventHandler<BeforeActionEvent> beforeActionEventHandler = this::addLife;
    private List<? extends Entity> shared;
    private int last = 0;

    @Override
    protected void doApply(Entity target) {
        final Self self = getSelf();
        shared = getOwn().getAllShikigami();
        for (Entity entity : shared) {
            entity.getEventController().add(this);
            entity.getEventController().add(BeforeActionEvent.class, beforeActionEventHandler, 300);
            entity.getBuffController().add(new JuanLiuBuff(getName(), getReduceDamage(), self));
        }
        last = getLast();
    }

    @Override
    protected void doBeforeAction() {
        if (last > 0) {
            last -= 1;
            if (last <= 0)
                remove(null);
        }
    }

    private void remove(DieEvent event) {
        for (Entity entity : shared)
            entity.getBuffController().remove(JuanLiuBuff.class);
    }

    private void addLife(BeforeActionEvent event) {
        event.getEntity().addLife((int) (getSelf().getMaxLife() * getAddLifePct()));
    }

    @Override
    protected EventHandler<DieEvent> getDieHandler() {
        return this::remove;
    }

    @Override
    public void handle(DamageShareEvent event) {
        final int damage = (int) (event.getTotal() / shared.size());
        event.getType().setJuanLiu(true);
        for (Entity entity : shared)
            getController().directDamage(event.getEntity(), entity, damage, event.getType());
        event.setLeft(damage);
    }

    @Override
    public String getName() {
        return "涓流";
    }

    @Override
    public int getFire() {
        return 3;
    }

    /**
     * @return 减速持续回合。
     */
    public int getLast() {
        return 2;
    }

    /**
     * @return 每回合回复椒图的生命百分比的量。不受减疗影响。
     */
    public double getAddLifePct() {
        return 0.05;
    }

    /**
     * @return 减伤。
     */
    public double getReduceDamage() {
        return 0.05;
    }

    public class JuanLiuBuff extends NumIBuff {
        JuanLiuBuff(String name, double reduceDamage, Entity src) {
            super(Integer.MAX_VALUE, name, -reduceDamage, src);
        }

        @Override
        public final double getBeDamage() {
            return value;
        }

        @Override
        public final void onRemove(Entity self) {
            self.getEventController().remove(JuanLiu.this);
            self.getEventController().remove(beforeActionEventHandler);
            shared.remove(self);
        }
    }
}
