package com.lpl.tank.factory;

import com.lpl.tank.Group;

import java.awt.*;

/**
 * @Classname BaseTank
 * @Description TODO
 * @Date 2021/1/31 21:56
 * @Created by lplmbp
 */
public abstract class BaseTank {
    public boolean living = true;
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();
    public abstract void paint(Graphics g);

    public Group getGroup() {
        return this.group;
    }

    public  void die(){
            this.living = false;
    };

    public abstract int getX();

    public abstract int getY();
}
