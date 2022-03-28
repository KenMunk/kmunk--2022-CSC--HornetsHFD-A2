package org.csc133.a2.states;

import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.GameState;

public class GameStarting implements GameState {

    @Override
    public void update(GameWorld context) {

    }

    public String dialogTitle(){
        return("Error");
    }

    public String dialogMessage(GameWorld context){
        return("You shouldn't be seeing this message");
    }

    public void dialogYes(GameWorld context){
        context.exit();
    }

    public void dialogNo(GameWorld context){
        context.exit();
    }
}
