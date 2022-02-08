package com.jiangjf.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 坦克
 *
 * @author jiangjf
 * @date 2022/2/8
 */
public class Tank {
    private int x, y;
    static final int SPEED = 5;
    static final int TANK_WIDTH = 50, TANK_HEIGHT = 50;
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private TankFrame tankFrame = null;
    private Color color = null;

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank() {
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
//        Color originColor = g.getColor();
//        if (this.color != null) {
//            g.setColor(this.color);
//        }
//        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        BufferedImage bufferedImage = null;
        switch (dir) {
            case LEFT:
                bufferedImage = ResourceMgr.tankLeft;
                break;
            case RIGHT:
                bufferedImage = ResourceMgr.tankRight;
                break;
            case UP:
                bufferedImage = ResourceMgr.tankUp;
                break;
            default:
            case DOWN:
                bufferedImage = ResourceMgr.tankDown;
                break;
        }
        g.drawImage(bufferedImage, x, y, null);
//        g.setColor(originColor);
        move();
    }

    /**
     * 移动位置
     */
    private void move() {
        if (!moving) {
            return;
        }
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
    }

    /**
     * 开火
     */
    public void fire() {
        int x = this.x;
        int y = this.y;
        switch (dir) {
            case LEFT:
                y = y + TANK_HEIGHT / 2;
                break;
            case RIGHT:
                x = x + TANK_WIDTH;
                y = y + TANK_HEIGHT / 2;
                break;
            case UP:
                x = x + TANK_WIDTH / 2;
                break;
            case DOWN:
                x = x + TANK_WIDTH / 2;
                y = y + TANK_HEIGHT;
                break;
            default:
                break;
        }
        this.tankFrame.bullets.add(new Bullet(x, y, this.dir, this.tankFrame));
    }
}
