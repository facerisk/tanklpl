package com.lpl.tank;

import java.awt.*;

/**
 * @Classname Bullet
 * @Description 子弹类
 * @Date 2021/1/14 22:29
 * @Created by lplmbp
 */
public class Bullet extends GameObject {
    //速度
    private static final int SPEED = Integer.parseInt((String) PropertyMgr.get("bulletSpeed"));
    ;
    //大小
    public static int WIDTH = ResourceMgr.getInstance().bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().bulletD.getHeight();
    private Dir dir;

    private boolean living = true;


    public Group group = Group.BAD;

    public Rectangle rect = new Rectangle();


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        //直接在子弹类中初始化碰撞检测的对象
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
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
            GameModel.getInstance().remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.getInstance().bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.getInstance().bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getInstance().bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getInstance().bulletD, x, y, null);
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

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        //子弹飞出游戏界面则死亡
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
    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return true;

        //todo:用一个rect来记录子弹位置:这样超占内存，要等jvm的垃圾回收（每重画一次就要产生这两个对象）
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        //判断是否碰撞
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            GameModel.getInstance().add(new Explode(ex, ey));
            return false;
        }
        return true;
    }

    public void die() {
        this.living = false;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
