package com.jiangjf.tank.collider;

import com.jiangjf.tank.*;

/**
 * 子弹和墙碰撞
 *
 * @author jiangjf
 * @date 2022/2/26
 */
public class BulletWallCollider implements Collider {
    @Override
    public Boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if (bullet.getRectangle().intersects(wall.getRectangle())) {
                bullet.die();
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return this.collide(o2, o1);
        }
        return true;
    }
}
