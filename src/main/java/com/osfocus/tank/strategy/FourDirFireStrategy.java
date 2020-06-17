package com.osfocus.tank.strategy;

import com.osfocus.tank.Bullet;
import com.osfocus.tank.Dir;
import com.osfocus.tank.GameModel;
import com.osfocus.tank.Tank;
import com.osfocus.tank.decorator.TailDecorator;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bX = t.x + t.WIDTH/2;
        int bY = t.y + t.HEIGHT/2;

        GameModel.getInstance().add(new TailDecorator(new Bullet(bX, bY, Dir.UP, t.group)));
        GameModel.getInstance().add(new Bullet(bX, bY, Dir.DOWN, t.group));
        GameModel.getInstance().add(new Bullet(bX, bY, Dir.LEFT, t.group));
        GameModel.getInstance().add(new Bullet(bX, bY, Dir.RIGHT, t.group));
    }
}
