package org.csc133.a2.gameobjects.Movables;

import org.csc133.a2.interfaces.HelicopterEngineState;

public class HelicopterEngineOff implements HelicopterEngineState {

    public HelicopterEngineState updateRotor(Helicopter chopper){
        HelicopterEngineState returnState = this;


        return(returnState);
    }

    public HelicopterEngineState toggle(Helicopter chopper){
        System.out.println("Attempting to start the helicopter");

        HelicopterEngineState returnState =
                new HelicopterEngineStarting();

        return(returnState);
    }

    @Override
    public String toString(){
        return("Engine is off");
    }

}
