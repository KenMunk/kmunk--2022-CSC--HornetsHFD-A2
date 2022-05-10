package org.csc133.a2.gameobjects.Movables;

import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Helipad;

public class UnPlayableHelicopter extends Helicopter{
    public UnPlayableHelicopter(Point initialPos) {
        super(initialPos);
    }

    public UnPlayableHelicopter(Helipad helipad) {
        super(helipad);
    }
}
