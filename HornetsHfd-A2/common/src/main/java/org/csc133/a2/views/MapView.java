package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.GameObject;


public class MapView extends Container {

    private GameWorld gameWorld;
    Point containerOffset;

    public MapView(GameWorld referenceOfGameWorld){
        containerOffset = new Point(0,275);
        gameWorld = referenceOfGameWorld;

    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        Display referenceDisplay = Display.getInstance();

        int screenWidth = referenceDisplay.getDisplayWidth();
        int screenHeight = referenceDisplay.getDisplayHeight();

        context.fillRect(-1,275,screenWidth+2,screenHeight-325);

        for
        (
                GameObject aGameObject
                :
                GameWorld.getInstance().getGameObjects()
        ){

            aGameObject.draw(context,containerOffset);
            // [TODO] um... render the map view I guess...

        }//*/




    }

}
