package com.osfocus.tank;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bX = t.x + t.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + t.HEIGHT/2 - Bullet.HEIGHT/2;

        new Bullet(bX, bY, t.dir, t.group, t.gm);

        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav"));
    }
}
