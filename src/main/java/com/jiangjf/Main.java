package com.jiangjf;

/**
 * tank war
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
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
