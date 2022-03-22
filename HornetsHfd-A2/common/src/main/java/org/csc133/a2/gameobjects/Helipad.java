package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Helipad extends Fixed {

    Point upperBound;
    Point lowerBound;
    private int width;

    public Helipad(Point position){
        this.setPos(position);
        this.updateBounds();
    }

    @Override
    public boolean containsPoint(Point checkPoint){
        return(

            this.upperBound.getX() > checkPoint.getX()
            &&
            this.lowerBound.getX() < checkPoint.getX()

            &&

            this.upperBound.getY() > checkPoint.getY()
            &&
            this.lowerBound.getY() < checkPoint.getY()

        );
    }

    public void updateBounds(){
        //Get display width
        Display thisDisplay = Display.getInstance();

        int width = thisDisplay.getDisplayWidth();
        int height = thisDisplay.getDisplayHeight();

        int offsetValue = width/30;
        this.width = offsetValue*2;

        //Create offset origin position

        this.lowerBound = new Point(0, 0);
        this.lowerBound.setX(this.getPos().getX() - offsetValue);
        this.lowerBound.setY(this.getPos().getY() - offsetValue);

        this.upperBound = new Point(0,0);
        this.upperBound.setX(this.getPos().getX() + offsetValue);
        this.upperBound.setY(this.getPos().getY() + offsetValue);

    }

    @Override
    public void draw(Graphics gfxContext, Point containerOrigin){

        //Draw the helipad
        //this.updateBounds();

        gfxContext.setColor(ColorUtil.GRAY);


        gfxContext.drawRect(
                this.lowerBound.getX() + containerOrigin.getX(),
                this.lowerBound.getY() + containerOrigin.getY(),
                this.width,
                this.width,
                5
        );

        gfxContext.drawArc(
                this.lowerBound.getX() + containerOrigin.getX(),
                this.lowerBound.getY() + containerOrigin.getY(),
                this.width,
                this.width,
                0,360
        );

    }
}
