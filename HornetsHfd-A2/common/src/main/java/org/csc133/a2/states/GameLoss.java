package org.csc133.a2.states;

import com.codename1.ui.Dialog;
import org.csc133.a2.*;
import org.csc133.a2.interfaces.GameState;

public class GameLoss implements GameState{

    public void update(GameWorld context){

    }

    public String dialogTitle(){
        return("Game Over");
    }

    public String dialogMessage(GameWorld context){
        return("You've failed to save the world");
    }

    public String yesOption(){
        return("Play Again");
    }

    public String noOption(){
        return("Run Away");
    }

    public void dialogYes(GameWorld context){
        context.startGame();
    }

    public void dialogNo(GameWorld context){
        context.exit();
    }

}
