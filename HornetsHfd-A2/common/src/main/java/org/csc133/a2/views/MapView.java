package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.GameObject;


public class MapView extends Container {

    private GameWorld gameWorld;

    public MapView(GameWorld referenceOfGameWorld){

        gameWorld = referenceOfGameWorld;

    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        /**
        for
        (
                GameObject aGameObject
                :
                GameWorld.getInstance().getGameObjects()
        ){

            // [TODO] um... render the map view I guess...

        }//*/




    }

}
