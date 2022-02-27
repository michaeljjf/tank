package com.jiangjf.tank;

import java.awt.*;

/**
 * 墙
 *
 * @author jiangjf
 * @date 2022/2/27
 */
public class Wall extends GameObject {
    private int width, height;

    private Rectangle rectangle;

    public Rectangle getRectangle() {
        return rectangle;
    }

    private Wall() {

    }

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        Color originColor = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(this.x, this.y, this.width, this.height);
        g.setColor(originColor);
    }
}
