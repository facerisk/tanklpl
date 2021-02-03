package com.lpl.tank.cor;

import com.lpl.tank.Bullet;
import com.lpl.tank.GameObject;
import com.lpl.tank.Tank;
import com.lpl.tank.Wall;

/**
 * @Classname BulletTankCollider
 * @Description TODO
 * @Date 2021/2/2 21:10
 * @Created by lplmbp
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if (t.rect.intersects(w.rect)) {
                t.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;


    }
}
