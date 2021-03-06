package com.lpl.tank;

import com.lpl.netty.Client;

/**
 * @Classname T
 * @Description TODO
 * @Date 2021/1/5 23:45
 * @Created by lplmbp
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //封装到TankFram，使代码简洁
        TankFrame tf = TankFrame.INSTANCE;
        tf.setVisible(true);

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        //加单独线程，否则主线程，不会向下运行
        new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();


        Client.INSTANCE.connect();

    }
}
