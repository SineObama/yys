package com.sine.yys.skill;

import com.sine.yys.attacktype.AttackTypeImpl;
import com.sine.yys.buff.NumIBuff;
import com.sine.yys.event.AfterActionEvent;
import com.sine.yys.event.DamageShareEvent;
import com.sine.yys.info.TransferType;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

import java.util.ArrayList;
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
    private static final String LAST = "JuanLiu_last";
    private final EventHandler<AfterActionEvent> afterActionHandler = new AfterActionHandler();
    private List<? extends Entity> shared;

    @Override
    protected void doApply(Entity target) {
        final Entity self = getSelf();
        shared = getOwn().getAllShikigami();
        for (Entity entity : shared) {
            entity.getEventController().add(this);
            entity.getEventController().add(AfterActionEvent.class, afterActionHandler, 300);
            entity.getBuffController().add(new JuanLiuBuff(getName(), getReduceDamage(), self));
        }
        getSelf().put(LAST, getLast());
    }

    @Override
    protected void doBeforeAction() {
        int last = getSelf().get(LAST, 0);
        if (last > 0) {
            last -= 1;
            getSelf().put(LAST, last);
            if (last <= 0)
                remove();
        }
    }

    private void remove() {
        for (Entity entity : new ArrayList<>(shared)) {
            final JuanLiuBuff juanLiuBuff = entity.getBuffController().get(JuanLiuBuff.class);
            if (juanLiuBuff != null && juanLiuBuff.getSrc() == getSelf())
                entity.getBuffController().remove(juanLiuBuff);
        }
    }

    @Override
    protected void onDie() {
        remove();
    }

    @Override
    public void handle(DamageShareEvent event) {
        if (event.getType().isJuanLiu()) // 薙魂可以再被涓流分摊，涓流后不再判断涓流
            return;
        final int damage = (int) (event.getType().getDamage() / shared.size());
        for (Entity entity : new ArrayList<>(shared))
            if (entity != event.getTarget()) {
                AttackType type = new AttackTypeImpl(event.getType(), TransferType.JUAN_LIU);
                type.setDamage(damage);
                getController().attack(event.getEntity(), entity, type);
            }
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

    class AfterActionHandler implements EventHandler<AfterActionEvent> {
        @Override
        public void handle(AfterActionEvent event) {
            final double v = getSelf().getMaxLife() * getAddLifePct();
            event.getEntity().addLife(getController().calcCritical(getSelf(), v));
        }
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
            self.getEventController().remove(afterActionHandler);
            shared.remove(self);
        }
    }
}
