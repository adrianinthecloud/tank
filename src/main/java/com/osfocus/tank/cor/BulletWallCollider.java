package com.osfocus.tank.cor;

import com.osfocus.tank.Bullet;
import com.osfocus.tank.GameObject;
import com.osfocus.tank.Tank;
import com.osfocus.tank.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;

            if (b.rect.intersects(w.rect)) {
                b.die();
                return true;
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return false;
    }
}
