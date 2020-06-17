package com.osfocus.tank;

import com.osfocus.tank.cor.BulletTankCollider;
import com.osfocus.tank.cor.Collider;
import com.osfocus.tank.cor.ColliderChain;
import com.osfocus.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private final Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD,this);
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();

    List<GameObject> objects = new ArrayList<>();
    Collider collider = new BulletTankCollider();
    Collider collider2 = new TankTankCollider();

    ColliderChain chain = new ColliderChain();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            objects.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
        }

        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("Number of Bullets: " + bullets.size(), 10, 60);
//        g.drawString("Number of Enemies: " + tanks.size(), 10, 80);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);

                chain.collide(o1, o2);
            }
        }
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//            if (!it.next().isAlive()) {
//                it.remove();
//            }
//        }

        // collision detection
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
