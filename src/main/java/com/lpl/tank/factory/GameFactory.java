package com.lpl.tank.factory;

import com.lpl.tank.*;

/**
 * @Classname AbstractFactory
 * @Description 抽象工厂
 * @Date 2021/1/26 22:39
 * @Created by lplmbp
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
