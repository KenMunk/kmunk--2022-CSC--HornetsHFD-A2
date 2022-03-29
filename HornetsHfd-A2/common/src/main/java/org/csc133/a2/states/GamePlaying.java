package org.csc133.a2.states;

import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.GameState;

public class GamePlaying implements GameState{

    public void update(GameWorld context){

        context.sendSparks();
        context.updateBurns();
        context.calculateBuildingBurns();
        context.updateHelicopter();
    }

    public String dialogTitle(){
        return("Error");
    }

    public String dialogMessage(GameWorld context){
        return("You shouldn't be seeing this message");
    }

    public String yesOption(){
        return("Go fix it");
    }

    public String noOption(){
        return(yesOption());
    }

    public void dialogYes(GameWorld context){
        context.exit();
    }

    public void dialogNo(GameWorld context){
        context.exit();
    }
}
