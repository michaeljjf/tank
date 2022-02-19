package com.jiangjf.tank;

import com.jiangjf.tank.myabstract.BaseExplode;
import com.jiangjf.tank.utils.Audio;
import com.jiangjf.tank.utils.ResourceMgr;

import java.awt.*;

/**
 * 第二种爆炸
 *
 * @author jiangjf
 * @date 2022/2/9
 */
public class SecondExplode extends BaseExplode {
    private int x, y;
    private TankFrame tankFrame;
    private int step = 0;

    private SecondExplode() {
    }

    public SecondExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(() -> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        int width = ResourceMgr.getInstance().secondExplodes[step].getWidth();
        int height = ResourceMgr.getInstance().secondExplodes[step].getHeight();
        g.drawImage(ResourceMgr.getInstance().secondExplodes[step], this.x - width / 2, this.y - height / 2, null);
        step++;
        if (step >= ResourceMgr.getInstance().secondExplodes.length) {
            this.tankFrame.explodes.remove(this);
        }
    }
}
