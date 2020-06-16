package com.osfocus.tank;

import com.osfocus.tank.abtractfactory.BaseTank;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(BaseTank t) {
        int bX = t.x + t.WIDTH/2;
        int bY = t.y + t.HEIGHT/2;

        new Bullet(bX, bY, Dir.UP, t.group, t.tf);
        new Bullet(bX, bY, Dir.DOWN, t.group, t.tf);
        new Bullet(bX, bY, Dir.LEFT, t.group, t.tf);
        new Bullet(bX, bY, Dir.RIGHT, t.group, t.tf);
    }
}
