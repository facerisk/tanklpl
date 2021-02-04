package com.lpl.tank;

import java.awt.*;

/**
 * @Classname Bullet
 * @Description 爆炸类
 * @Date 2021/1/14 22:29
 * @Created by lplmbp
 */
public class Explode extends GameObject {
    public static int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

    private int x, y;

//    private boolean living = true;


    private int step = 0;


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    /**
     * @MethodName paint
     * @param: g
     * @Return void
     * @Decription 自己画自己
     * @Author lipengliang
     * @date 2021/1/14 22:33
     */
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.getInstance().explodes[step++], x, y, null);

        if (step >= ResourceMgr.getInstance().explodes.length) {
            //删除爆炸
            GameModel.getInstance().remove(this);
        }


    }


}
