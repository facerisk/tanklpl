package com.lpl.tank.observer;

import com.lpl.tank.Tank;

/**
 * @Classname TankFireHandler
 * @Description 观察者
 * @Date 2021/2/13 22:05
 * @Created by lplmbp
 */
public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank t = e.getSource();
        t.fire();

    }
}
