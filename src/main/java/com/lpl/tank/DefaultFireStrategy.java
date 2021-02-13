package com.lpl.tank;

import com.lpl.tank.decorator.RectDecorator;

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

        //bug? new Bullet() 画了两次
//        GameModel.getInstance().add(new RectDecorator(new Bullet(bx, by, t.dir, t.group)));

        new Bullet(bx, by, t.dir, t.group);
        if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
