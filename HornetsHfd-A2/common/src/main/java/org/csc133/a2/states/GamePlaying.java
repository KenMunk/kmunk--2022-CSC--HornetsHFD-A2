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

}
