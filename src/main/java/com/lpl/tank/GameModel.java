package com.lpl.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname GameModel
 * @Description 门面
 * @Date 2021/2/2 20:14
 * @Created by lplmbp
 */
public class GameModel {


    //主坦克
    Tank myTank = new Tank(200, 300, Dir.DOWN, Group.GOOD, this);
    //子弹
    java.util.List<Bullet> bullets = new ArrayList<Bullet>();
    //敌方坦克
    java.util.List<Tank> tanks = new ArrayList<Tank>();
    //爆炸
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN,Group.BAD, this));
        }

    }

    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + bullets.size(), 10, 60);
        g.drawString("敌人数量：" + tanks.size(), 10, 80);
        g.drawString("爆炸数量：" + explodes.size(), 10, 100);
        g.setColor(color);

        //不破坏对象的封装，让坦克自己画处自己
        myTank.paint(g);

        //子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }



        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

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
