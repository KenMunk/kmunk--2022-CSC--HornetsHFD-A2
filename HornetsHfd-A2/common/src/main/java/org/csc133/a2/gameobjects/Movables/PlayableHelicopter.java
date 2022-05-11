package org.csc133.a2.gameobjects.Movables;

import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Helipad;

public class PlayableHelicopter extends Helicopter{
    protected static PlayableHelicopter player;

    private PlayableHelicopter(Point initialPos) {
        super(initialPos);
    }

    private PlayableHelicopter(Helipad helipad) {
        super(helipad);
    }

    public PlayableHelicopter getInstance(Helipad helipad){
        if(player == null){
            player = new PlayableHelicopter(helipad);
        }
        return(player);
    }
}
