package com.osfocus.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel {
    private final Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Number of Bullets: " + bullets.size(), 10, 60);
        g.drawString("Number of Enemies: " + tanks.size(), 10, 80);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
            if (!it.next().isAlive()) {
                it.remove();
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        // collision detection
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
