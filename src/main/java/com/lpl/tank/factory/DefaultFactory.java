package com.lpl.tank.factory;

import com.lpl.tank.*;

/**
 * @Classname FristFactory
 * @Description TODO
 * @Date 2021/1/26 22:50
 * @Created by lplmbp
 */
public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group,TankFrame tf) {
        return new Bullet(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }
}
