package com.lpl.tank.cor;

import com.lpl.tank.Bullet;
import com.lpl.tank.Explode;
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
            if (b.group == t.getGroup()) return true;

            //todo:用一个rect来记录子弹位置:这样超占内存，要等jvm的垃圾回收（每重画一次就要产生这两个对象）
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            //判断是否碰撞
            if (b.rect.intersects(t.rect)) {
                t.die();
                b.die();
                int ex = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int ey = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                t.gm.add(new Explode(ex, ey, t.gm));
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;


    }
}
