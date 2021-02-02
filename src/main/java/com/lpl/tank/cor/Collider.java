package com.lpl.tank.cor;

import com.lpl.tank.GameObject;

/**
 * @Classname Collider
 * @Description TODO
 * @Date 2021/2/2 21:07
 * @Created by lplmbp
 */
public interface Collider {
    void collide(GameObject o1,GameObject o2);
}
