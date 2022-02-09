package com.jiangjf.tank;

import java.awt.*;

/**
 * 子弹
 *
 * @author jiangjf
 * @date 2022/2/8
 */
public class Bullet {
    private static final int SPEED = 15;
    private static final int WIDTH = ResourceMgr.bulletUp.getWidth();
    private static final int HEIGHT = ResourceMgr.bulletUp.getHeight();
    private int x, y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;
    private final Rectangle rectangle = new Rectangle();

    private Bullet() {
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        // 让子弹的坐标，在坦克中间
        this.x = x - WIDTH / 2;
        this.y = y - HEIGHT / 2;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!this.living) {
            this.tankFrame.bullets.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletRight, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletUp, x, y, null);
                break;
            case DOWN:
            default:
                g.drawImage(ResourceMgr.bulletDown, x, y, null);
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

    public void collideWith(Tank tank) {
        // 子弹的类型如果和坦克一样，不检查碰撞
        if (this.group.equals(tank.getGroup())) {
            return;
        }
        if (this.rectangle.intersects(tank.getRectangle()) && tank.getLiving()) {
            this.tankFrame.explodes.add(new Explode(tank.getX() + Tank.TANK_WIDTH / 2, tank.getY() + Tank.TANK_HEIGHT / 2, this.tankFrame));
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
