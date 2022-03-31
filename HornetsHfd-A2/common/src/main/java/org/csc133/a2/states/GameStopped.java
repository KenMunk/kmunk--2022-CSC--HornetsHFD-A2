package org.csc133.a2.states;

import com.codename1.ui.Dialog;
import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.GameState;

public class GameStopped implements GameState{

    public void update(GameWorld context){

        //Doing nothing because the game is stopped

    }

    public String dialogTitle(){
        return("Game Paused");
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
        context.pauseToggle();
    }

    public void dialogNo(GameWorld context){
        context.exit();
    }

}
