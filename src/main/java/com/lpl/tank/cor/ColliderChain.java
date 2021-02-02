package com.lpl.tank.cor;

import java.util.LinkedList;
import java.util.List;

/**
 * @Classname ColliderChain
 * @Description TODO
 * @Date 2021/2/2 21:44
 * @Created by lplmbp
 */
public class ColliderChain {
    private List<Collider> colliders = new LinkedList<>();

    public void add(Collider c){
        colliders.add(c);
    }
}
