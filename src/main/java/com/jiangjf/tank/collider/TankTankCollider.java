package com.jiangjf.tank.collider;

import com.jiangjf.tank.*;

/**
 * 坦克和坦克碰撞
 *
 * @author jiangjf
 * @date 2022/2/26
 */
public class TankTankCollider implements Collider {
    @Override
    public Boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
            if (tank1.getRectangle().intersects(tank2.getRectangle())) {
                // 要碰撞时，回到前一个位置
                tank1.back();
                tank2.back();
            }
        }
        return true;
    }
}
