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
public class Tank extends GameObject {
    static final int SPEED = 5;
    public static final int TANK_WIDTH = ResourceMgr.getInstance().tankLeft.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.getInstance().tankLeft.getHeight();
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private GameModel gameModel = null;
    private static final Random RANDOM = new Random();
    private boolean living = true;
    private Group group;
    private final Rectangle rectangle = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public boolean getLiving() {
        return this.living;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    private Tank() {
    }

    public Tank(int x, int y, Dir dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = TANK_WIDTH;
        rectangle.height = TANK_HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            this.gameModel.remove(this);
            return;
        }
        boolean isGood = this.group.equals(Group.GOOD);
        switch (dir) {
            case LEFT:
                g.drawImage(isGood ? ResourceMgr.getInstance().tankLeft : ResourceMgr.getInstance().badTankLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(isGood ? ResourceMgr.getInstance().tankRight : ResourceMgr.getInstance().badTankRight, x, y, null);
                break;
            case UP:
                g.drawImage(isGood ? ResourceMgr.getInstance().tankUp : ResourceMgr.getInstance().badTankUp, x, y, null);
                break;
            default:
            case DOWN:
                g.drawImage(isGood ? ResourceMgr.getInstance().tankDown : ResourceMgr.getInstance().badTankDown, x, y, null);
                break;
        }
        move();
    }

    /**
     * 移动位置
     */
    private void move() {
        this.prevX = this.x;
        this.prevY = this.y;
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                this.x -= SPEED;
                break;
            case RIGHT:
                this.x += SPEED;
                break;
            case UP:
                this.y -= SPEED;
                break;
            case DOWN:
                this.y += SPEED;
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
        this.gameModel.add(new Bullet(x, y, this.dir, this.group, this.gameModel));
    }

    public void die() {
        this.living = false;
    }

    public void back() {
        this.x = this.prevX;
        this.y = this.prevY;
    }
}
