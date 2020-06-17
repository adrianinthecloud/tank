package com.osfocus.tank.cor;

import com.osfocus.tank.Bullet;
import com.osfocus.tank.GameObject;
import com.osfocus.tank.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.backward(false);
                t2.backward(true);
            }
        }

        return true;
    }
}
