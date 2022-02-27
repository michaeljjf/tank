package com.jiangjf.tank.collider;

import com.jiangjf.tank.Bullet;
import com.jiangjf.tank.GameObject;
import com.jiangjf.tank.Tank;
import com.jiangjf.tank.Wall;

/**
 * 坦克和墙碰撞
 *
 * @author jiangjf
 * @date 2022/2/26
 */
public class TankWallCollider implements Collider {
    @Override
    public Boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.getRectangle().intersects(wall.getRectangle())) {
                tank.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return this.collide(o2, o1);
        }
        return true;
    }
}
