package org.csc133.a2.states;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.interfaces.HelicopterEngineState;

public class HelicopterEngineRunning implements HelicopterEngineState {

    public HelicopterEngineState updateRotor(Helicopter chopper){
        HelicopterEngineState returnState = this;

        //move
        chopper.move();

        return(returnState);
    }


    public HelicopterEngineState toggle(Helicopter chopper){
        HelicopterEngineState returnState = this;

        if(chopper.getSpeed() < 0.01f){
            returnState = new HelicopterEngineStopping();
        }

        return(returnState);
    }

}
