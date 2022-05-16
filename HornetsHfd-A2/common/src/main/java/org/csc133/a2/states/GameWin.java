package org.csc133.a2.states;

import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.GameState;

public class GameWin implements GameState {

    public void update(GameWorld context){
        //really isn't much to do here
    }

    public String dialogTitle(){
        return("VICTORY");
    }

    public String dialogMessage(GameWorld context){
        return("You've saved the world with $" + context.getPoints() + " left");
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
