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
    private static final int SPEED = 15;
    //大小
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x, y;
    private Dir dir;

    private boolean living = true;

    TankFrame tf = null;

    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
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
        if (!living) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

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
            living = false;
    }

    public boolean isLiving() {
        return living;
    }

    /**
     * @MethodName collideWith
     * @param: tank
     * @Return void
     * @Decription 碰撞死亡
     * @Author lipengliang
     * @date 2021/1/19 23:26
     */
    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;

        //todo:用一个rect来记录子弹位置
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        //判断是否碰撞
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();

            tf.explodes.add(new Explode(x,y,tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
