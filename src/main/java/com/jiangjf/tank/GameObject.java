package com.jiangjf.tank;

import java.awt.*;

/**
 * 所有物体的基类
 *
 * @author jiangjf
 * @date 2022/2/24
 */
public abstract class GameObject {
    protected int x, y;
    protected int prevX, prevY;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * 子类实现
     *
     * @param g Graphics
     */
    public abstract void paint(Graphics g);
}
