package com.jiangjf.tank.collider;

import com.jiangjf.tank.*;

/**
 * 子弹和坦克碰撞
 *
 * @author jiangjf
 * @date 2022/2/26
 */
public class BulletTankCollider implements Collider {
    @Override
    public Boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            // 子弹的类型如果和坦克一样，不检查碰撞
            if (bullet.getGroup().equals(tank.getGroup())) {
                return true;
            }
            if (bullet.getRectangle().intersects(tank.getRectangle()) && tank.getLiving()) {
                GameModel.getInstance().add(new Explode(tank.getX() + Tank.TANK_WIDTH / 2, tank.getY() + Tank.TANK_HEIGHT / 2));
                bullet.die();
                tank.die();
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return this.collide(o2, o1);
        }
        return true;
    }
}
