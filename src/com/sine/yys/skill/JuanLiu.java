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
 */
public class JuanLiu extends BaseNoTargetSkill implements EventHandler<DamageShareEvent> {
    private final EventHandler<BeforeActionEvent> beforeActionEventHandler = this::add;
    private List<? extends Entity> shared;
    private int last = 0;

    @Override
    protected void doApply(Entity target) {
        final Self self = getSelf();
        shared = getOwn().getAllShikigami();
        for (Entity entity : shared) {
            entity.getEventController().add(this);
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

    private void add(BeforeActionEvent event) {
        event.getEntity().addLife((int) (getSelf().getMaxLife() * getAddLifePct()));
    }

    @Override
    protected EventHandler<DieEvent> getDieHandler() {
        return this::remove;
    }

    @Override
    public void handle(DamageShareEvent event) {
        final int damage = (int) (event.getTotal() / shared.size());
        event.getType().setJiaoTu(true);
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
     * @return 驱散我方减益。
     */
    public double getReduceDamage() {
        return 0.05;
    }

    class JuanLiuBuff extends NumIBuff {
        /**
         * @param name         名称前缀。
         * @param reduceDamage 减伤百分比。
         * @param src          来源式神。
         */
        JuanLiuBuff(String name, double reduceDamage, Entity src) {
            super(Integer.MAX_VALUE, name, -reduceDamage, src);
        }

        @Override
        public double getBeDamage() {
            return value;
        }

        @Override
        public void onRemove(Entity self) {
            self.getEventController().remove(JuanLiu.this);
            self.getEventController().remove(beforeActionEventHandler);
            shared.remove(self);
        }
    }
}
