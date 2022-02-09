package com.jiangjf.tank;

import java.awt.*;

/**
 * @author jiangjf
 * @date 2022/2/9
 */
public class Explode {
    private int x, y;
    private static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    private static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private TankFrame tankFrame;
    private int step = 0;

    private Explode() {
    }

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], this.x - WIDTH / 2, this.y - HEIGHT / 2, null);
        if (step >= ResourceMgr.explodes.length) {
            this.tankFrame.explodes.remove(this);
        }
    }
}
