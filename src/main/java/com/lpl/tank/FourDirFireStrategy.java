package com.lpl.tank;

import com.lpl.tank.decorator.RectDecorator;
import com.lpl.tank.decorator.TailDecorator;

/**
 * @Classname FourDirFireStategy
 * @Description TODO
 * @Date 2021/1/26 20:25
 * @Created by lplmbp
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            //bug? new Bullet() 画了两次
//            GameModel.getInstance().add(
//                    new RectDecorator(new TailDecorator(
//                            new Bullet(bx, by, dir, t.group))));

            new Bullet(bx, by, dir, t.group);
        }


        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
