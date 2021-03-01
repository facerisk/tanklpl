package com.lpl.tank.observer;

import com.lpl.tank.Tank;

import java.io.Serializable;

/**
 * @Classname TankFireObserver
 * @Description 观察者接口
 * @Date 2021/2/13 22:06
 * @Created by lplmbp
 */
public interface TankFireObserver extends Serializable {

    void actionOnFire(TankFireEvent e);
}
