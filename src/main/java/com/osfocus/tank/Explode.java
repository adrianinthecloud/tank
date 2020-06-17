package com.osfocus.tank;

import java.awt.*;

public class Explode extends GameObject {
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x, y;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        new Audio("audio/explode.wav").start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            GameModel.getInstance().remove(this);
        }
    }
}
