package com.lpl.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Classname GameObject
 * @Description TODO
 * @Date 2021/2/2 20:43
 * @Created by lplmbp
 */
public abstract class GameObject  implements Serializable {
    public int x,y;

    public abstract void paint(Graphics g);

    public abstract int getWidth();
    public abstract int getHeight();
}
