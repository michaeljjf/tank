package com.jiangjf.tank;


import java.awt.*;
import java.util.Random;

/**
 * tank war
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        // 初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(50 + i * 80, 200, Dir.DOWN, tankFrame);
//            tank.setMoving(true);
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
