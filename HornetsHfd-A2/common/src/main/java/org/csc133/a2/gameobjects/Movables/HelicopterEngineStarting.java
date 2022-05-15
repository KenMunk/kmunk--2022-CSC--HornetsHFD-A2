package org.csc133.a2.gameobjects.Movables;

import org.csc133.a2.interfaces.HelicopterEngineState;

public class HelicopterEngineStarting implements HelicopterEngineState {

    public HelicopterEngineState updateRotor(Helicopter chopper){
        HelicopterEngineState returnState = this;

        chopper.spinRotorUp();
        chopper.burnFuel();

        if(chopper.isReady()){
            returnState = new HelicopterEngineRunning();
        }

        return(returnState);
    }

    public HelicopterEngineState toggle(Helicopter chopper){
        HelicopterEngineState returnState = new HelicopterEngineStopping();

        return(returnState);
    }


    @Override
    public String toString(){
        return("Engine is starting");
    }

}
