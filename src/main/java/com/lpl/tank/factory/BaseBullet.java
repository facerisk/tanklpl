package com.lpl.tank.factory;

import com.lpl.tank.Tank;

import java.awt.*;

/**
 * @Classname BaseBullet
 * @Description TODO
 * @Date 2021/1/31 21:56
 * @Created by lplmbp
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collideWith(BaseTank tank);
}
