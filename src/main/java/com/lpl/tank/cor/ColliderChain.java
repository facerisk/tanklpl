package com.lpl.tank.cor;

import com.lpl.tank.GameObject;
import com.lpl.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname ColliderChain
 * @Description 责任链
 * @Date 2021/2/2 21:44
 * @Created by lplmbp
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        String colliders = (String) PropertyMgr.get("colliders");
        List<String> colliderList = Arrays.asList(colliders.split(","));
        for (String collider : colliderList) {
            try {
                add((Collider) Class.forName(collider).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        add(new BulletTankCollider());
//        add(new TankTankCollider());

    }

    public void add(Collider c) {
        colliders.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) {
                //如果某个链条件符合中止，则余下链不执行
                return false;
            }
        }
        return true;
    }
}
