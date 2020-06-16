package com.osfocus.tank.abtractfactory;

import com.osfocus.tank.*;

import java.awt.*;

public abstract class BaseTank {
    protected static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static int WIDTH = ResourceMgr.badTankD.getWidth();
    public static int HEIGHT = ResourceMgr.badTankD.getHeight();
    public Rectangle rect = new Rectangle();

    protected boolean alive = true;
    public int x = 200;
    public int y = 200;
    public Dir dir = Dir.DOWN;
    public Group group = Group.BAD;
    public TankFrame tf = null;

    public BaseTank(boolean alive, int x, int y, Dir dir, Group group, TankFrame tf) {
        this.alive = alive;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public abstract void paint(Graphics g);

    public Group getGroup() {
        return group;
    }

    public void die() {
        alive = false;
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
