package com.osfocus.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank {
    int x = 200;
    int y = 200;
    Dir dir = Dir.DOWN;
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");

    public static int WIDTH = ResourceMgr.badTankD.getWidth();
    public static int HEIGHT = ResourceMgr.badTankD.getHeight();

    public Rectangle rect = new Rectangle();

    private Random random = new Random();
    private boolean moving = true;
    Group group = Group.BAD;

    GameModel gm = null;
    FireStrategy fs = null;

    private boolean alive = true;

    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        try {
            fs = (FireStrategy) Class.forName(PropertyMgr.get(group == Group.GOOD ? "goodTankFire" : "badTankFire")
                                        .toString()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        if (!alive) gm.tanks.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
        }

        move();
    }

    private void move() {
        if (!moving) return;

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

        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire();;

        if (group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheck();

        rect.x = x;
        rect.y = y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        this.alive = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
