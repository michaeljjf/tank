package com.jiangjf.tank.collider;

import com.jiangjf.tank.GameObject;

/**
 * @author jiangjf
 * @date 2022/2/26
 */
public interface Collider {
    /**
     * 碰撞检测
     *
     * @param o1 GameObject
     * @param o2 GameObject
     * @return {@link Boolean}
     */
    Boolean collide(GameObject o1, GameObject o2);
}
