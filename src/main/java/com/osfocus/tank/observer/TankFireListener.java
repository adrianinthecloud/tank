package com.osfocus.tank.observer;

import com.osfocus.tank.Tank;

import java.io.Serializable;

public class TankFireListener implements TankFireObserver, Serializable {
    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank source = event.getSource();
        source.fire();
    }
}
