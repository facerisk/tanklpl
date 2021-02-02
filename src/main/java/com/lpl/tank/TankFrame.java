package com.lpl.tank;

import jdk.nashorn.internal.ir.CallNode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TankFrame
 * @Description 继承Frame, 应该算是主类
 * @Date 2021/1/14 20:00
 * @Created by lplmbp
 */
public class TankFrame extends Frame {
    GameModel gm = new GameModel();

    static final int GAME_WIDTH = Integer.parseInt((String)PropertyMgr.get("gameWidth"));
    static final int GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.get("gameHeight"));

    /**
     * @MethodName TankFrame
     * @param:
     * @Return
     * @Decription 继承后可以直接使用父类方法
     * @Author lipengliang
     * @date 2021/1/14 20:04
     */
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
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
     * @MethodName update
     * @param: g
     * @Return
     * @Decription 双缓冲, 解决屏幕闪烁问题，mac并没有这个问题。。。
     * 原理是repaint 刷新屏幕画笔时，会先调用update方法，所以我们重写了update方法
     * @Author lipengliang
     * @date 2021/1/14 22:50
     */
    //内存中定义一个图片
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        //拿到图片上的画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        //将图片画成黑色，长高为窗口长高
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        //图片上的画笔调用paint方法，画出坦克与子弹
        paint(gOffScreen);
        //最终屏幕上的画笔，将内存中的带有坦克与子弹的图片，整体刷新出来
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /**
     * 画图
     */
    @Override
    public void paint(Graphics g) {
        gm.paint(g);

    }


    /**
     * @Decription 自定义键盘监听
     * @Author lipengliang
     * @Date 2021/1/14
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
                case KeyEvent.VK_SPACE:
                    gm.getMainTank().fire();
                    break;
                default:
                    break;

            }
            serMainTankDir();
        }

        //键盘方向对应方向
        private void serMainTankDir() {
            Tank myTank = gm.getMainTank();
            //判断坦克是否静止
            if (!bL && !bU && !bR & !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);

                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }


        }
    }
}
