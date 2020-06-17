package com.osfocus.tank.cor;

import com.osfocus.tank.GameObject;
import com.osfocus.tank.Tank;
import com.osfocus.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            if (t.getRect().intersects(w.rect)) {
                t.backward();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank){
            return this.collide(o2, o1);
        }

        return false;
    }
}
