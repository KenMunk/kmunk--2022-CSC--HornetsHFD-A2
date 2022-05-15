package org.csc133.a2.interfaces;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Movables.Helicopter;

public interface HelicopterEngineState {

    //will take the helicopter the state of the helicopter
    public HelicopterEngineState updateRotor(Helicopter chopper);

    public HelicopterEngineState toggle(Helicopter chopper);

    @Override
    public String toString();
}
