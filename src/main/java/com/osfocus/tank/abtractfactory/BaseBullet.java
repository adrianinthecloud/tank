package com.osfocus.tank.abtractfactory;

import com.osfocus.tank.Group;
import com.osfocus.tank.PropertyMgr;
import com.osfocus.tank.TankFrame;

import java.awt.*;

public abstract class BaseBullet {
    protected static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    protected final TankFrame tf;
    protected boolean alive = true;
    protected Group group = Group.BAD;

    public BaseBullet(boolean alive, Group group, TankFrame tf) {
        this.alive = true;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
    }

    public abstract void collideWith(BaseTank baseTank);

    public boolean isAlive() {
        return alive;
    }
}
