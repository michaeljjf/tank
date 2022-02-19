package com.jiangjf.tank.myabstract;

import com.jiangjf.tank.SecondExplode;
import com.jiangjf.tank.TankFrame;
import com.jiangjf.tank.enums.Dir;
import com.jiangjf.tank.enums.Group;
import com.jiangjf.tank.strategy.FireStrategy;

/**
 * 第二种效果的工厂实现
 *
 * @author jiangjf
 * @date 2022/2/19
 */
public class SecondFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame, FireStrategy fireStrategy) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new SecondExplode(x, y, tankFrame);
    }
}
