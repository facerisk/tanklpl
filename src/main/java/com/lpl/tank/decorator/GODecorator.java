package com.lpl.tank.decorator;

import com.lpl.tank.GameObject;

import java.awt.*;

/**
 * @Classname GODecorator
 * @Description 装饰类
 * @Date 2021/2/4 23:00
 * @Created by lplmbp
 */
public abstract class GODecorator extends GameObject {

    //聚合GameObject
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g) ;
}
