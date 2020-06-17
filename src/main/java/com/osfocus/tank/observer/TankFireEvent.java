package com.osfocus.tank.observer;

import com.osfocus.tank.Tank;

public class TankFireEvent {
    private Tank source;

    public TankFireEvent(Tank source) {
        this.source = source;
    }

    public Tank getSource() {
        return source;
    }
}
