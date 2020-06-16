package com.osfocus.tank;

import com.osfocus.tank.abtractfactory.BaseTank;

import java.awt.*;
import java.util.Random;

public class Tank extends BaseTank {
    private Random random = new Random();
    private boolean moving = true;

    FireStrategy fs = null;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super(true, x, y, dir, group, tf);
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
        if (!alive) tf.tanks.remove(this);

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
        if (this.x > TankFrame.GAME_WIDTH - BaseTank.WIDTH - 2) x = TankFrame.GAME_WIDTH - BaseTank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - BaseTank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - BaseTank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
