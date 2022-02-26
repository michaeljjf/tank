package com.jiangjf.tank;

import com.jiangjf.tank.collider.BulletTankCollider;
import com.jiangjf.tank.collider.Collider;
import com.jiangjf.tank.collider.ColliderChain;
import com.jiangjf.tank.enums.Dir;
import com.jiangjf.tank.enums.Group;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用Facade模式，管理相关对象，TankFrame只需要和GameModel打交道，再由GameModel与Tank、Bullet、Explode打交道
 *
 * @author jiangjf
 * @date 2022/2/24
 */
public class GameModel {
    private final Tank myTank = new Tank(210, 400, Dir.UP, Group.GOOD, this);
    List<GameObject> objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    public Tank getMainTank() {
        return this.myTank;
    }

    public void add(GameObject object) {
        this.objects.add(object);
    }

    public void remove(GameObject object) {
        this.objects.remove(object);
    }

    public void paint(Graphics g) {
        // 画出我方坦克
        myTank.paint(g);

        // 画出敌方坦克、子弹、爆炸效果（不能用foreach，因为迭代器会发生数组越界for不会）
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // 简单的碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                chain.collide(objects.get(i), objects.get(j));
            }
        }
    }
}
