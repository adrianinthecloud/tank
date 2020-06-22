package com.osfocus.tank.strategy;

import com.osfocus.tank.*;
import com.osfocus.tank.decorator.RectDecorator;

import java.io.Serializable;

public class DefaultFireStrategy implements FireStrategy, Serializable {
    @Override
    public void fire(Tank t) {
        int bX = t.x + t.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + t.HEIGHT/2 - Bullet.HEIGHT/2;

        GameModel.getInstance().add(new RectDecorator(new Bullet(bX, bY, t.dir, t.group)));

        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav"));
    }
}
