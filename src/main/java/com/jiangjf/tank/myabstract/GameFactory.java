package com.jiangjf.tank.myabstract;

import com.jiangjf.tank.TankFrame;
import com.jiangjf.tank.enums.Dir;
import com.jiangjf.tank.enums.Group;
import com.jiangjf.tank.strategy.FireStrategy;

/**
 * 抽象工厂模式
 *
 * @author jiangjf
 * @date 2022/2/19
 */
public abstract class GameFactory {
    /**
     * 创建坦克
     *
     * @param x            x坐标
     * @param y            y坐标
     * @param dir          方向
     * @param group        分组
     * @param tankFrame    坦克窗口对象
     * @param fireStrategy 开火策略
     * @return {@link BaseTank}
     */
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame, FireStrategy fireStrategy);

    /**
     * 创建子弹
     *
     * @param x         x坐标
     * @param y         y坐标
     * @param dir       方向
     * @param group     分组
     * @param tankFrame 坦克窗口对象
     * @return {@link BaseBullet}
     */
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame);

    /**
     * 创建爆炸效果
     *
     * @param x         x坐标
     * @param y         y坐标
     * @param tankFrame 坦克窗口对象
     * @return {@link BaseExplode}
     */
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
}
