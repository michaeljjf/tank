package com.jiangjf.tank.collider;

import com.jiangjf.tank.*;

/**
 * @author jiangjf
 * @date 2022/2/26
 */
public class BulletTankCollider implements Collider {
    @Override
    public void collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            // 子弹的类型如果和坦克一样，不检查碰撞
            if (bullet.getGroup().equals(tank.getGroup())) {
                return;
            }
            if (bullet.getRectangle().intersects(tank.getRectangle()) && tank.getLiving()) {
                TankFrame.GAME_MODEL.add(new Explode(tank.getX() + Tank.TANK_WIDTH / 2, tank.getY() + Tank.TANK_HEIGHT / 2, TankFrame.GAME_MODEL));
                bullet.die();
                tank.die();
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            this.collideWith(o2, o1);
        }
    }
}
