package com.jiangjf.tank;

import com.jiangjf.tank.myabstract.BaseExplode;
import com.jiangjf.tank.utils.Audio;
import com.jiangjf.tank.utils.ResourceMgr;

import java.awt.*;

/**
 * 爆炸
 *
 * @author jiangjf
 * @date 2022/2/9
 */
public class Explode extends BaseExplode {
    private int x, y;
    private static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    private static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();
    private TankFrame tankFrame;
    private int step = 0;

    private Explode() {
    }

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(() -> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.getInstance().explodes[step++], this.x - WIDTH / 2, this.y - HEIGHT / 2, null);
        if (step >= ResourceMgr.getInstance().explodes.length) {
            this.tankFrame.explodes.remove(this);
        }
    }
}
