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
    Tank myTank = new Tank(200,200,Dir.DOWN);

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


    /**
     * 画图
     */
    @Override
    public void paint(Graphics g) {
        //不破坏对象的封装，让坦克自己画处自己
        myTank.paint(g);

    }

    /**
     *  @Decription 自定义键盘监听
     *  @Author lipengliang
     *  @Date 2021/1/14
     */
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;

            }

            serMainTankDir();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;

            }
            serMainTankDir();
        }

        //键盘方向对应方向
        private void serMainTankDir() {
            //判断坦克是否静止
            if(!bL&&!bU&&!bR&!bD){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);

                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }



        }
    }
}
