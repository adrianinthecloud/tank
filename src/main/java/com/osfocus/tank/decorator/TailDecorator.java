package com.osfocus.tank.decorator;

import com.osfocus.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator {
    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(x, y, x+getWidth(), y);
        g.setColor(c);
    }
}
