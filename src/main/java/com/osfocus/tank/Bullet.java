package com.osfocus.tank;

import com.osfocus.tank.abtractfactory.BaseBullet;
import com.osfocus.tank.abtractfactory.BaseTank;

import java.awt.*;

public class Bullet extends BaseBullet {
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private int x, y;
    private final Dir dir;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        super(true, group, tf);

        this.x = x;
        this.y = y;
        this.dir = dir;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!alive) {
            this.tf.bullets.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:
        }

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            alive = false;
        }

        rect.x = x;
        rect.y = y;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()) return;

        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + BaseTank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + BaseTank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
        }
    }

    private void die() {
        this.alive = false;
    }
}
