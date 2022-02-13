package com.jiangjf.tank;

import com.jiangjf.tank.strategy.DefaultFireStrategy;
import com.jiangjf.tank.enums.Dir;
import com.jiangjf.tank.enums.Group;

/**
 * 坦克大战
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        // 初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(50 + i * 120, 70, Dir.DOWN, Group.BAD, tankFrame);
            tank.setMoving(true);
            tankFrame.tanks.add(tank);
        }

        while (true) {
            tankFrame.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
