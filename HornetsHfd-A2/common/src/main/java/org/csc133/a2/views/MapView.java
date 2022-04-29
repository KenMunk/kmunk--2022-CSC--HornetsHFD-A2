package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.GameWorld;
import org.csc133.a2.ViewOffsets;
import org.csc133.a2.gameobjects.GameObject;


public class MapView extends Container {

    private GameWorld gameWorld;
    private ViewOffsets viewOffsets;

    public MapView(GameWorld referenceOfGameWorld,
                   ViewOffsets viewOffsets){
        gameWorld = referenceOfGameWorld;
        this.viewOffsets = viewOffsets;
    }

    public void displayTransform(Graphics context){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(getAbsoluteX(), getAbsoluteY());

        transform.translate(0,getHeight()-30);
        transform.scale(1,-1f);

        transform.translate(-getAbsoluteX(),-getAbsoluteY());
        context.setTransform(transform);
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        Point parentOrigin = new Point
        (
            this.getX(),
            this.getY()
        );
        Point screenOrigin = new Point
        (
            getAbsoluteX(),
            getAbsoluteY()
        );

        Display referenceDisplay = Display.getInstance();

        int screenWidth = referenceDisplay.getDisplayWidth();
        int screenHeight = referenceDisplay.getDisplayHeight();

        context.setColor(ColorUtil.BLACK);

        context.fillRect
        (
            getAbsoluteX(),
            getAbsoluteY()-60,
            getWidth(),
            getHeight()+60
        );

        displayTransform(context);



        //[TODO] change the rendering paradigm

        //Since this is where the border between codenameone
        //coordinate standards and local coordinate standards

        GameWorld.getInstance().draw(context,parentOrigin,screenOrigin);

        context.resetAffine();

        /*
        for
        (
                GameObject aGameObject
                :
                GameWorld.getInstance().getGameObjects()
        ){

            aGameObject.draw(context,new Point(getAbsoluteX(),
                    getAbsoluteY()-130));

        }//*/




    }

    public void update(){
        this.repaint();
    }


    @Override
    public Component getNextFocusDown(){
        return(getParent());
    }

    @Override
    public Component getNextFocusUp(){
        return(getParent());
    }

    @Override
    public Component getNextFocusLeft(){
        return(getParent());
    }

    @Override
    public Component getNextFocusRight(){
        return(getParent());
    }


}
