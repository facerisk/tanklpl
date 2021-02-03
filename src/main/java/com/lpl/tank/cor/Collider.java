package com.lpl.tank.cor;

import com.lpl.tank.GameObject;

/**
 * @Classname Collider
 * @Description 碰撞，责任链是实现该接口
 * @Date 2021/2/2 21:07
 * @Created by lplmbp
 */
public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}
