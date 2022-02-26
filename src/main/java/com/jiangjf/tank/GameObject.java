package com.jiangjf.tank;

import com.jiangjf.tank.enums.Group;

import java.awt.*;

/**
 * 所有物体的基类
 *
 * @author jiangjf
 * @date 2022/2/24
 */
public abstract class GameObject {
    protected Group group;
    public boolean living = true;
    protected int x, y;
    protected Rectangle rectangle = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean getLiving() {
        return this.living;
    }

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
