package com.osfocus.tank.cor;

import com.osfocus.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
