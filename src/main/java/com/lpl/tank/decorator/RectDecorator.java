package com.lpl.tank.decorator;

import com.lpl.tank.GameObject;

import java.awt.*;

/**
 * @Classname RectDecorator
 * @Description TODO
 * @Date 2021/2/4 23:02
 * @Created by lplmbp
 */
public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(super.go.x, super.go.y, getWidth(), getHeight());
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
