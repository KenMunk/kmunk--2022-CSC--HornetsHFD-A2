package org.csc133.a2.gameobjects.Fixed;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class River extends Fixed{


    private int width;

    public River(Point origin){

        //I'm going to give the river a fixed position with
        //respect to the screen but the lower bounds of
        //thickness will be dynamic out of fairness.
        //
        this.setPos(origin);
        this.updateWidth();

    }

    public void updateWidth(){
        Display thisDisplay = Display.getInstance();
        int displayHeight = thisDisplay.getDisplayHeight();

        int tempWidth = (displayHeight/10);
        this.width = tempWidth;
    }

    public boolean containsPoint(Point reference){
        return(
                reference.getY() >= this.getPos().getY()
                        &&
                        reference.getY() <= this.getPos().getY()+this.width
        );
    }

    @Override
    public void draw(Graphics gfxContext, Point containerPoint){

        //Get the current display width and update river
        this.updateWidth();

        //then we render
        gfxContext.setColor(ColorUtil.CYAN);
        gfxContext.drawRect(this.getPos().getX() + containerPoint.getX(),
                this.getPos().getY() + containerPoint.getY(),
                Display.getInstance().getDisplayWidth()
                        +100,
                this.width,
                20);



    }

}
