package com.lpl.com;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
