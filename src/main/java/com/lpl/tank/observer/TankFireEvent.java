package com.lpl.tank.observer;

import com.lpl.tank.Tank;

/**
 * @Classname TankFireEvent
 * @Description 观察者 事件
 * @Date 2021/2/13 22:02
 * @Created by lplmbp
 */
public class TankFireEvent {
    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
