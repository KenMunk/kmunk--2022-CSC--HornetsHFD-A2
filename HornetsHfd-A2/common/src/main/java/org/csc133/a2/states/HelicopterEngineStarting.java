package org.csc133.a2.states;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.interfaces.HelicopterEngineState;

public class HelicopterEngineStarting implements HelicopterEngineState {

    public HelicopterEngineState updateRotor(Helicopter chopper){
        HelicopterEngineState returnState = this;

        chopper.spinRotorUp();

        if(chopper.isReady()){
            returnState = new HelicopterEngineRunning();
        }

        return(returnState);
    }

    public HelicopterEngineState toggle(Helicopter chopper){
        HelicopterEngineState returnState = new HelicopterEngineStopping();

        return(returnState);
    }

}
