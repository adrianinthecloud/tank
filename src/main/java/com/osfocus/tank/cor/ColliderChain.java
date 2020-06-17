package com.osfocus.tank.cor;

import com.osfocus.tank.GameObject;
import com.osfocus.tank.PropertyMgr;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        StringTokenizer token = new StringTokenizer((String) PropertyMgr.get("colliders"), ",");
        while (token.hasMoreTokens()) {
            try {
                colliders.add((Collider) Class.forName(token.nextToken()).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (colliders.get(i).collide(o1, o2)) {
                return true;
            }
        }

        return false;
    }
}
