package test.components.util;

import com.sine.yys.entity.ShikigamiEntityImpl;

/**
 * 辅助生命值检查。
 */
public class LifeTest {
    int life;
    int shield = 0;
    ShikigamiEntityImpl entity;

    public LifeTest(ShikigamiEntityImpl entity) {
        this.entity = entity;
        this.life = entity.getMaxLife();
    }

    public void addShield(double count) {
        shield += count;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = (int) life;
        entity.setLife((int) life);
    }

    public void test(double change, String message) {
        if (change < 0) {
            shield += change;
            if (shield < 0) {
                change = shield;
                shield = 0;
            } else {
                change = 0;
            }
        }
        life += change;
        assert life == entity.getLifeInt() : message + ". " + life + " == " + entity.getLifeInt();
    }
}
