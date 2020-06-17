package com.osfocus.tank.decorator;

import com.osfocus.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator {
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, getWidth(), getHeight());
        g.setColor(c);
    }
}
