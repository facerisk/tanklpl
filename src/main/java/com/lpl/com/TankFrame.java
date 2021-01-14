package com.lpl.com;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Classname TankFrame
 * @Description 继承Frame, 应该算是主类
 * @Date 2021/1/14 20:00
 * @Created by lplmbp
 */
public class TankFrame extends Frame {
    int x = 200, y = 200;

    /**
     * @MethodName TankFrame
     * @param:
     * @Return
     * @Decription 继承后可以直接使用父类方法
     * @Author lipengliang
     * @date 2021/1/14 20:04
     */
    public TankFrame() {
        setSize(800, 600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        //键盘监听
        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //实现窗口点击叉关闭
                System.exit(0);
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);
        x += 10;
//        y += 10;

    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            x += 200;

            repaint();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            y += 200;

            repaint();
        }
    }
}
