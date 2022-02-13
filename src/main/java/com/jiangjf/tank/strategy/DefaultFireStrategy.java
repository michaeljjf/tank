package com.jiangjf.tank.strategy;

import com.jiangjf.tank.Bullet;
import com.jiangjf.tank.Tank;


/**
 * 打出一颗子弹
 *
 * @author jiangjf
 * @date 2022/2/13
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        // 中心位置
        int x = tank.getX() + Tank.TANK_WIDTH / 2;
        int y = tank.getY() + Tank.TANK_HEIGHT / 2;
        new Bullet(x, y, tank.getDir(), tank.getGroup(), tank.getTankFrame());
    }
}
