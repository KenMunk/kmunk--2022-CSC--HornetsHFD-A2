package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.GameWorld;
import org.csc133.a2.ViewOffsets;
import org.csc133.a2.gameobjects.GameObject;


public class MapView extends Container {

    private GameWorld gameWorld;
    private ViewOffsets viewOffsets;
    Point containerOffset;

    public MapView(GameWorld referenceOfGameWorld,
                   ViewOffsets viewOffsets){
        containerOffset = new Point(0,325);
        gameWorld = referenceOfGameWorld;
        this.viewOffsets = viewOffsets;
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        Display referenceDisplay = Display.getInstance();

        int screenWidth = referenceDisplay.getDisplayWidth();
        int screenHeight = referenceDisplay.getDisplayHeight();

        context.setColor(ColorUtil.BLACK);

        context.fillRect
        (
            -1,
            this.getAbsoluteY(),
            screenWidth+2,
            this.getHeight()
        );

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

    public void update(){
        this.repaint();
    }

}
