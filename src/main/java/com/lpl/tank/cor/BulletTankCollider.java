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
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            return b.collideWith(t);
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;


    }
}
