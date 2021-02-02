package com.lpl.tank;

/**
 * @Classname DefaultFireStrategy
 * @Description 默认发射策略
 * @Date 2021/1/26 20:11
 * @Created by lplmbp
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bx, by, t.dir, t.group, t.gm);

        if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
