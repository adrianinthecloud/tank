package com.osfocus.tank.strategy;

import com.osfocus.tank.Bullet;
import com.osfocus.tank.Dir;
import com.osfocus.tank.Tank;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bX = t.x + t.WIDTH/2;
        int bY = t.y + t.HEIGHT/2;

        new Bullet(bX, bY, Dir.UP, t.group, t.gm);
        new Bullet(bX, bY, Dir.DOWN, t.group, t.gm);
        new Bullet(bX, bY, Dir.LEFT, t.group, t.gm);
        new Bullet(bX, bY, Dir.RIGHT, t.group, t.gm);
    }
}
