package com.lpl.tank;

/**
 * @Classname T
 * @Description TODO
 * @Date 2021/1/5 23:45
 * @Created by lplmbp
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //封装到TankFram，使代码简洁
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN,Group.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        //不断刷新画笔
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
