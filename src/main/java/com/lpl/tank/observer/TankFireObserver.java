package com.lpl.tank.observer;

import com.lpl.tank.Tank;

/**
 * @Classname TankFireObserver
 * @Description 观察者接口
 * @Date 2021/2/13 22:06
 * @Created by lplmbp
 */
public interface TankFireObserver {

    void actionOnFire(TankFireEvent e);
}
