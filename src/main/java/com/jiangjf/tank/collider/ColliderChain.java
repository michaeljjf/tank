package com.jiangjf.tank.collider;

import com.jiangjf.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 碰撞链（责任链模式）
 *
 * @author jiangjf
 * @date 2022/2/26
 */
public class ColliderChain implements Collider {
    List<Collider> colliderList = new LinkedList<>();

    public ColliderChain() {
        this.add(new BulletTankCollider());
        this.add(new TankTankCollider());
    }

    public void add(Collider collider) {
        colliderList.add(collider);
    }

    @Override
    public void collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliderList.size(); i++) {
            colliderList.get(i).collide(o1, o2);
        }
    }
}
