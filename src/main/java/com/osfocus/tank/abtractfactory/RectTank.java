package com.osfocus.tank.abtractfactory;

import com.osfocus.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    private Random random = new Random();
    private boolean moving = true;

    FireStrategy fs;

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super(true, x, y, dir, group, tf);
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        System.out.println("Group = " + group + " " + PropertyMgr.get(group == Group.GOOD ? "goodTankFire" : "badTankFire"));
        try {
            fs = (FireStrategy) Class.forName(PropertyMgr.get(group == Group.GOOD ? "goodTankFire" : "badTankFire")
                    .toString()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fire() {
//        fs.fire(this);

        int bX = this.x + RectTank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + RectTank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            tf.gf.createBullet(bX, bY, dir, group, tf);
        }

        if (group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    public boolean isMoving() {
        return moving;
    }

    private void move() {

        if (!moving)
            return;

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

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();
        // update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2)
            x = 2;
        if (this.y < 28)
            y = 28;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2)
            x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2)
            y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void paint(Graphics g) {
        if (!alive)
            tf.tanks.remove(this);

        Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x, y, 40, 40);
        g.setColor(c);
        move();
    }
}
