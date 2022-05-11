package org.csc133.a2.gameobjects.Movables;

import org.csc133.a2.interfaces.HelicopterEngineState;

public class HelicopterEngineStopping implements HelicopterEngineState {

    public HelicopterEngineState updateRotor(Helicopter chopper){
        HelicopterEngineState returnState = this;

        chopper.spinRotorDown();

        if(!chopper.isRunning()){
            returnState = new HelicopterEngineOff();
        }

        return(returnState);
    }


    public HelicopterEngineState toggle(Helicopter chopper){
        HelicopterEngineState returnState = new HelicopterEngineStarting();

        return(returnState);
    }
}
