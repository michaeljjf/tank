package com.jiangjf.tank.strategy;

import com.jiangjf.tank.Tank;

/**
 * 开火策略
 *
 * @author jiangjf
 * @date 2022/2/13
 */
public interface FireStrategy {
    /**
     * 开火
     *
     * @param tank 坦克对象
     */
    void fire(Tank tank);
}
