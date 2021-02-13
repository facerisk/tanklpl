package com.lpl.tank;


import com.lpl.tank.observer.TankFireEvent;
import com.lpl.tank.observer.TankFireHandler;
import com.lpl.tank.observer.TankFireObserver;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Classname Tank
 * @Description 面向对象 -抽象-> 封装坦克类
 * 该类属性都为私有，不允许他人直接修改，某些属性提供getset方法
 * @Date 2021/1/14 21:56
 * @Created by lplmbp
 */
public class Tank extends GameObject {
    int oldX, oldY;
    //默认向下
    Dir dir = Dir.DOWN;
    //常量不允许他人修改
    private static final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));


    public static int WIDTH = ResourceMgr.getInstance().goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().goodTankU.getHeight();

    private Random random = new Random();

    //是否静止
    private boolean moving = true;
    //持有窗口的引用，使坦克能发射子弹
//    TankFrame tf;

    private boolean living = true;
    Group group = Group.BAD;


    public Rectangle rect = new Rectangle();

    FireStrategy fs;


    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        GameModel.getInstance().add(this);

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if (group == Group.GOOD) {
            String goodFS = (String) PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFS).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            fs = new DefaultFireStrategy();
        }
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRect() {
        return rect;
    }


    /**
     * @MethodName paint
     * @param: g
     * @Return void
     * @Decription 坦克画出自己，这样有良好的面向对象思想
     * @Author lipengliang
     * @date 2021/1/14 22:05
     */
    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.getInstance().goodTankL : ResourceMgr.getInstance().badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.getInstance().goodTankU : ResourceMgr.getInstance().badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.getInstance().goodTankR : ResourceMgr.getInstance().badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.getInstance().goodTankD : ResourceMgr.getInstance().badTankD, x, y, null);
                break;
        }

        move();

    }

    private void move() {
        //每次移动前记录当前位置
        oldX = x;
        oldY = y;
        //坦克默认静止
        if (!moving) return;

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

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 96) {
            randomDir();
        }

        boundsCheck();

        //放在边界检测之后 update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    /**
     * @Decription 边界检测
     * @Author lipengliang
     * @Date 2021/1/24
     */
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }

    }

    /**
     * @Decription 产生随机方向
     * @Author lipengliang
     * @Date 2021/1/24
     */
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @Decription 发射子弹
     * @Author lipengliang
     * @Date 2021/1/24
     */
    public void fire() {
        fs.fire(this);
    }

    /**
     * @Decription 死亡
     * @Author lipengliang
     * @Date 2021/1/24
     */
    public void die() {
        this.living = false;
    }

    /**
     * @Decription 停止
     * @Author lipengliang
     * @Date 2021/2/2
     */
    public void stop() {
        this.moving = false;
    }

    /**
     * @Decription 碰撞时返还上一个位置
     * @Author lipengliang
     * @Date 2021/2/2
     */
    public void back() {
        x = oldX;
        y = oldY;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    /**
     *  @Decription 观察者  事件源
     *  @Author lipengliang
     *  @Date 2021/2/13
     */
    private List<TankFireObserver> fireObservers = Arrays.asList(new TankFireHandler());
    public void handleFireKey(){
        TankFireEvent event = new TankFireEvent(this);
        for (TankFireObserver o : fireObservers) {
            o.actionOnFire(event);
        }
    }
}
