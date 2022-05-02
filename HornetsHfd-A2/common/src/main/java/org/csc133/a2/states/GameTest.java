package org.csc133.a2.states;

import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.GameState;

public class GameTest implements GameState {

    public void update(GameWorld context){

        //Doing nothing because the game is stopped
        context.sendSparks();
        context.updateBurns();

    }

    public String dialogTitle(){
        return("");
    }

    public String dialogMessage(GameWorld context){
        return("Would you like to continue?");
    }

    public String yesOption(){
        return("Continue");
    }

    public String noOption(){
        return("Run Away");
    }

    public void dialogYes(GameWorld context){
    }

    public void dialogNo(GameWorld context){
        context.exit();
    }
}
