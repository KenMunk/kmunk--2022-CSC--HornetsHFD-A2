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
    Point offsetOrigin;
    float offsetSide;
    float scaleFactor;

    public MapView(GameWorld referenceOfGameWorld,
                   ViewOffsets viewOffsets){
        gameWorld = referenceOfGameWorld;
        this.viewOffsets = viewOffsets;
        offsetSide = 0;
        scaleFactor = 1;
    }

    public void displayTransform(Graphics context){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);

        Display currentDisplay = Display.getInstance();
        float maxHeight = 1440;
        float maxWidth = 1920;

        float widthScale = getWidth()/(maxWidth);
        float heightScale =
                (getHeight()-viewOffsets.getTotalOffset())/(maxHeight);

        scaleFactor = 1;

        if(widthScale<=heightScale){
            scaleFactor = widthScale;
        }
        else{
            scaleFactor = heightScale;
        }

        float offsetWidth = (getWidth() - (scaleFactor*maxWidth))/2;
        offsetSide = offsetWidth;
        float offsetHeight =
                ((getHeight()-viewOffsets.getTotalOffset()) -
                        (scaleFactor*maxHeight))/2;

        offsetOrigin = new Point((int)offsetWidth,(int)offsetHeight);

        transform.translate(getAbsoluteX(), getAbsoluteY());
        transform.translate(0,getHeight());
        transform.translate(offsetWidth,offsetHeight+45);
        transform.scale(scaleFactor,-scaleFactor);
        //transform.translate(-getAbsoluteX(),-getAbsoluteY());
        context.setTransform(transform);
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);
        context.resetAffine();

        context.setColor(ColorUtil.GRAY);

        context.fillRect
        (
            getAbsoluteX(),
            getAbsoluteY()-60,
            getWidth(),
            getHeight()+60
        );
        //*/

        displayTransform(context);

        int screenWidth = 1920;
        int screenHeight = 1440;

        context.setColor(ColorUtil.rgb(20,10,5));

        context.fillRect
        (
            0,
            0,
            screenWidth,
            screenHeight
        );

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

        //[TODO] change the rendering paradigm

        //Since this is where the border between codenameone
        //coordinate standards and local coordinate standards

        GameWorld.getInstance().draw(context,new Point(0,0),
                screenOrigin);

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

    public void pointerPressed(int x, int y){
        x = x - getAbsoluteX()-(int)offsetSide;
        y = y - getAbsoluteY()+10;

        int widthScaled = (int)(1920 * scaleFactor);
        int heightScaled = (int)(1440 * scaleFactor);

        float floatX = (x/(float)widthScaled);
        float floatY = 1-((y)/(float)heightScaled);

        int worldX = (int)(1920 * floatX);
        int worldY = (int)(1440 * floatY);


        System.out.println
        (
            "Press detected at: (" +
            worldX + ", " +
            worldY + ")"
        );

        if(
           worldX >= 0 && worldY >= 0 && worldX<=1920 && worldY<= 1440
        ){

            gameWorld.pointerPressed(new Point(worldX,worldY));
        }
    }

}
