package com.lpl.tank;

import java.awt.*;

/**
 * @Classname Wall
 * @Description TODO
 * @Date 2021/2/3 23:37
 * @Created by lplmbp
 */
public class Wall extends GameObject {
    int w, h;
    public Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
