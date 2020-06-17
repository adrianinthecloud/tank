package com.osfocus.tank.decorator;

import com.osfocus.tank.GameModel;
import com.osfocus.tank.GameObject;

import java.awt.*;

public class GODecorator extends GameObject {
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    public void paint(Graphics g) {
        go.paint(g);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
