package com.jiangjf.tank;

import java.awt.*;

/**
 * 子弹
 *
 * @author jiangjf
 * @date 2022/2/8
 */
public class Bullet {
    private static final int SPEED = 10;
    private static final int WIDTH = 10, HEIGHT = 10;
    private int x, y;
    private Dir dir;
    private boolean live = true;
    private TankFrame tankFrame = null;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        // 让子弹的坐标，在坦克中间
        this.x = x - WIDTH / 2;
        this.y = y - HEIGHT / 2;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!this.live) {
            this.tankFrame.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.live = false;
        }
    }

}
