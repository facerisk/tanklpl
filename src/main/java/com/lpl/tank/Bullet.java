package com.lpl.tank;

import java.awt.*;

/**
 * @Classname Bullet
 * @Description 子弹类
 * @Date 2021/1/14 22:29
 * @Created by lplmbp
 */
public class Bullet {
    //速度
    private static final int SPEED = 1;
    //大小
    private static int WIDTH = 5, WEIGHT = 5;
    private int x, y;
    private Dir dir;

    private boolean live = true;



    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
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
        if (!live) {
            tf.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);
        //圆形 大小
        g.fillOval(x, y, WIDTH, WEIGHT);
        //设置回原来颜色
        g.setColor(color);

        move();

    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }


        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            live = false;
    }

    public boolean isLive() {
        return live;
    }
}