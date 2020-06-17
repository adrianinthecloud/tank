package com.osfocus.tank.observer;

import com.osfocus.tank.Tank;

public class TankFireListener implements TankFireObserver {
    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank source = event.getSource();
        source.fire();
    }
}
