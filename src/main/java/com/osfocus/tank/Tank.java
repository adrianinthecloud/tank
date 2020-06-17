package com.osfocus.tank;

import com.osfocus.tank.observer.TankFireEvent;
import com.osfocus.tank.observer.TankFireListener;
import com.osfocus.tank.observer.TankFireObserver;
import com.osfocus.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tank extends GameObject {
    public int x = 200;
    public int y = 200;
    public Dir dir = Dir.DOWN;
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");

    public static int WIDTH = ResourceMgr.badTankD.getWidth();
    public static int HEIGHT = ResourceMgr.badTankD.getHeight();

    public Rectangle rect = new Rectangle();

    private Random random = new Random();
    private boolean moving = true;
    public Group group = Group.BAD;

    FireStrategy fs = null;

    private boolean alive = true;

    int oldX;
    int oldY;

    public Tank(int x, int y, Dir dir, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        try {
            fs = (FireStrategy) Class.forName(PropertyMgr.get(group == Group.GOOD ? "goodTankFire" : "badTankFire")
                                        .toString()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!alive) GameModel.getInstance().remove(this);

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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        if (!moving) return;

        oldX = x;
        oldY = y;

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

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void stop() {
        moving = false;
    }

    public void backward() {
        this.x = oldX;
        this.y = oldY;
    }

    List<TankFireObserver> observerList = Arrays.asList(new TankFireListener());
    public void handleFire() {
        TankFireEvent event = new TankFireEvent(this);

        for (TankFireObserver observer : observerList) {
            observer.actionOnFire(event);
        }
    }
}
