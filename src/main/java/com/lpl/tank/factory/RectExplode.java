package com.lpl.tank.factory;

import com.lpl.tank.Audio;
import com.lpl.tank.ResourceMgr;
import com.lpl.tank.TankFrame;

import java.awt.*;

/**
 * @Classname RectExplod
 * @Description TODO
 * @Date 2021/1/31 22:13
 * @Created by lplmbp
 */
public class RectExplode extends BaseExplode {
    public static int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

    private int x, y;

//    private boolean living = true;

    TankFrame tf = null;

    private int step = 0;


    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

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
    @Override
    public void paint(Graphics g) {

//        g.drawImage(ResourceMgr.getInstance().explodes[step++], x, y, null);

        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,10*step,10*step);
        g.setColor(c);
        step++;
        if (step >= ResourceMgr.getInstance().explodes.length) {
            //删除爆炸
            tf.explodes.remove(this);
        }


    }


}
