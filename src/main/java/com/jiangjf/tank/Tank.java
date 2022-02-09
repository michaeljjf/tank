package com.jiangjf.tank;

import com.jiangjf.tank.enums.Dir;
import com.jiangjf.tank.enums.Group;
import com.jiangjf.tank.utils.ResourceMgr;

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
    static final int SPEED = 5;
    static final int TANK_WIDTH = ResourceMgr.tankLeft.getWidth();
    static final int TANK_HEIGHT = ResourceMgr.tankLeft.getHeight();
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private boolean living = true;
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;
    private static final Random RANDOM = new Random();
    private final Rectangle rectangle = new Rectangle();

    public Rectangle getRectangle() {
        return rectangle;
    }

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

    public boolean getLiving() {
        return this.living;
    }

    private Tank() {
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = TANK_WIDTH;
        rectangle.height = TANK_HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living) {
            this.tankFrame.tanks.remove(this);
            return;
        }
        boolean isGood = this.group.equals(Group.GOOD);
        switch (dir) {
            case LEFT:
                g.drawImage(isGood ? ResourceMgr.tankLeft : ResourceMgr.badTankLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(isGood ? ResourceMgr.tankRight : ResourceMgr.badTankRight, x, y, null);
                break;
            case UP:
                g.drawImage(isGood ? ResourceMgr.tankUp : ResourceMgr.badTankUp, x, y, null);
                break;
            default:
            case DOWN:
                g.drawImage(isGood ? ResourceMgr.tankDown : ResourceMgr.badTankDown, x, y, null);
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
        if (this.group.equals(Group.BAD) && RANDOM.nextInt(100) > 96) {
            this.fire();
        }
        // 敌方坦克随机移动
        if (this.group.equals(Group.BAD) && RANDOM.nextInt(100) > 98) {
            this.randomDir();
        }
        boundsCheck();
        // update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    /**
     * 边界检测
     */
    private void boundsCheck() {
        if (this.x < 4) {
            this.x = 4;
        }
        if (this.y < 42) {
            this.y = 42;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.TANK_WIDTH - 4) {
            this.x = TankFrame.GAME_WIDTH - Tank.TANK_WIDTH - 4;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT - 4) {
            this.y = TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT - 4;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[RANDOM.nextInt(4)];
    }

    /**
     * 开火
     */
    public void fire() {
        // 中心位置
        int x = this.x + Tank.TANK_WIDTH / 2;
        int y = this.y + Tank.TANK_HEIGHT / 2;
        this.tankFrame.bullets.add(new Bullet(x, y, this.dir, this.group, this.tankFrame));
    }

    public void die() {
        this.living = false;
    }
}
