package com.osfocus.tank;

import com.osfocus.tank.abtractfactory.BaseTank;

public interface FireStrategy {
    void fire(BaseTank t);
}
