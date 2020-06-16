package com.osfocus.tank.abtractfactory;

import com.osfocus.tank.Dir;
import com.osfocus.tank.Explode;
import com.osfocus.tank.Group;
import com.osfocus.tank.TankFrame;

import java.awt.*;

public abstract class GameFactory {
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
}
