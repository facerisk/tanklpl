package com.lpl.tank;

import com.lpl.tank.cor.BulletTankCollider;
import com.lpl.tank.cor.Collider;
import com.lpl.tank.cor.ColliderChain;
import com.lpl.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname GameModel
 * @Description 门面模式，抽象出游戏各种事物
 * 对与子弹等游戏物体他有相当于调停者，统筹这些事物
 * @Date 2021/2/2 20:14
 * @Created by lplmbp
 */
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }


    //主坦克
    Tank myTank = null;

    ColliderChain chain = new ColliderChain();

    //调停者模式下，统筹管理
    private List<GameObject> objects = new ArrayList<>();



    private GameModel(){

    }

    public static GameModel getInstance(){
        return INSTANCE;
    }

    //初始化
    private void init(){
        myTank = new Tank(200, 300, Dir.DOWN, Group.GOOD);
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN,Group.BAD);
        }

        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(150,300,50,100));

    }



    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹数量：" + bullets.size(), 10, 60);
//        g.drawString("敌人数量：" + tanks.size(), 10, 80);
//        g.drawString("爆炸数量：" + explodes.size(), 10, 100);
        g.setColor(color);

        //不破坏对象的封装，让坦克自己画处自己
        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < objects.size() ; i++) {

            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);

                chain.collide(o1,o2);

            }
        }

//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }

        //java.util.ConcurrentModificationException
//        for(Bullet b : bullets){
//            b.paint(g);
//        }
        //在循环逻辑体中判断删除，同样不会报错
//        for(Iterator<Bullet> it = bullets.iterator();it.hasNext();){
//            Bullet b = it.next();
//            if(!b.isLive()) it.remove();
//            b.paint(g);
//        }

    }

    public Tank getMainTank() {

        return myTank;
    }
}
