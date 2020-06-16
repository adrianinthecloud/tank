package com.osfocus.tank.abtractfactory;

import com.osfocus.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet {
    private static final int SPEED = 6;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private int x, y;
    private Dir dir;

    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
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
        if(!alive) {
            tf.bullets.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(c);

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
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) alive = false;

    }

    public void collideWith(BaseTank tank) {
        if(this.group == tank.getGroup()) return;

        if(rect.intersects(tank.rect)) {
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
