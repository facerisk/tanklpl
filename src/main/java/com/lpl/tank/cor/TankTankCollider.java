package com.lpl.tank.cor;

import com.lpl.tank.Bullet;
import com.lpl.tank.GameObject;
import com.lpl.tank.Tank;

/**
 * @Classname BulletTankCollider
 * @Description TODO
 * @Date 2021/2/2 21:10
 * @Created by lplmbp
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.back();
                t2.back();
               //坦克撞不死，所以可以执行下一个链，永远为true
            }
        }
        return true;


    }
}
