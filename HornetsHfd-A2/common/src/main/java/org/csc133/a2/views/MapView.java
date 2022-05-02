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

        Display currentDisplay = Display.getInstance();
        int maxHeight = currentDisplay.getDisplayHeight();
        int maxWidth = currentDisplay.getDisplayWidth();

        float widthScale = getWidth()/((float)maxWidth);
        float heightScale = getHeight()/((float)maxHeight);

        transform.translate(getAbsoluteX(), getAbsoluteY());

        transform.translate(0,getHeight());

        if(widthScale<=heightScale){
            transform.scale(widthScale,-widthScale);
        }
        else{
            transform.scale(heightScale,-heightScale);
        }

        transform.translate(-getAbsoluteX(),-getAbsoluteY());
        context.setTransform(transform);
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);
        context.resetAffine();

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
