package org.csc133.a2.states;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.interfaces.HelicopterEngineState;

public class HelicopterEngineOff implements HelicopterEngineState {

    public HelicopterEngineState updateRotor(Helicopter chopper){
        HelicopterEngineState returnState = this;


        return(returnState);
    }

    public HelicopterEngineState toggle(Helicopter chopper){
        HelicopterEngineState returnState =
                new HelicopterEngineStarting();

        return(returnState);
    }

}
