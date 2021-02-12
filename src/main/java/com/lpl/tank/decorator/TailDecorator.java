package com.lpl.tank.decorator;

import com.lpl.tank.GameObject;

import java.awt.*;

/**
 * @Classname RectDecorator
 * @Description TODO
 * @Date 2021/2/4 23:02
 * @Created by lplmbp
 */
public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.x, go.y, go.x + getWidth(), go.y + getHeight());
        g.setColor(c);

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
