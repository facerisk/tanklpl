package com.lpl.tank.factory;

import com.lpl.tank.Dir;
import com.lpl.tank.Group;
import com.lpl.tank.TankFrame;

/**
 * @Classname RectFactory
 * @Description TODO
 * @Date 2021/1/31 22:15
 * @Created by lplmbp
 */
public class RectFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x,y,dir,group,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }
}
