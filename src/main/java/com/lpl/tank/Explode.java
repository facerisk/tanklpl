package com.lpl.tank;

import java.awt.*;

/**
 * @Classname Bullet
 * @Description 爆炸类
 * @Date 2021/1/14 22:29
 * @Created by lplmbp
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    private boolean living = true;

    TankFrame tf = null;

    private int step = 0;


    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Audio1("audio/explode.wav").run();
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

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if (step >= ResourceMgr.explodes.length) {
            step = 0;
        }
    }


}
