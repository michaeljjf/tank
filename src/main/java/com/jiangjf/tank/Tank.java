package com.jiangjf.tank;

import java.awt.*;
import java.util.Random;

/**
 * 坦克
 *
 * @author jiangjf
 * @date 2022/2/8
 */
public class Tank {
    private int x, y;
    static final int SPEED = 3;
    static final int TANK_WIDTH = ResourceMgr.tankLeft.getWidth();
    static final int TANK_HEIGHT = ResourceMgr.tankLeft.getHeight();
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private boolean living = true;
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;
    private static final Random random = new Random();

    public Group getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    private Tank() {
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!living) {
            this.tankFrame.tanks.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankRight, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankUp, x, y, null);
                break;
            default:
            case DOWN:
                g.drawImage(ResourceMgr.tankDown, x, y, null);
                break;
        }
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

        // 敌方坦克随机发射子弹
        if (this.group.equals(Group.BAD) && random.nextInt(15) == 8) {
            this.fire();
        }
    }

    /**
     * 开火
     */
    public void fire() {
        // 中心位置
//        int x = this.x + Tank.TANK_WIDTH / 2;
//        int y = this.y + Tank.TANK_HEIGHT / 2;
        // 让子弹发出的位置在坦克的炮口
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
        this.tankFrame.bullets.add(new Bullet(x, y, this.dir, this.group, this.tankFrame));
    }

    public void die() {
        this.living = false;
    }
}
