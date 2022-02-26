package com.jiangjf.tank;

import com.jiangjf.tank.enums.Dir;
import com.jiangjf.tank.enums.Group;
import com.jiangjf.tank.utils.ResourceMgr;

import java.awt.*;

/**
 * 子弹
 *
 * @author jiangjf
 * @date 2022/2/8
 */
public class Bullet extends GameObject {
    private static final int SPEED = 15;
    private static final int WIDTH = ResourceMgr.getInstance().bulletUp.getWidth();
    private static final int HEIGHT = ResourceMgr.getInstance().bulletUp.getHeight();
    private Dir dir;
    private GameModel gameModel = null;

    private Bullet() {
    }

    public Bullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        // 让子弹的坐标，在坦克中间
        this.x = x - WIDTH / 2;
        this.y = y - HEIGHT / 2;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.living) {
            this.gameModel.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.getInstance().bulletLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getInstance().bulletRight, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.getInstance().bulletUp, x, y, null);
                break;
            case DOWN:
            default:
                g.drawImage(ResourceMgr.getInstance().bulletDown, x, y, null);
                break;
        }
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
        // update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.living = false;
        }
    }

    public void die() {
        this.living = false;
    }
}
