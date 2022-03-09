package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class Building extends Fixed{

    public Building(Point origin, Dimension dimensions){

        setPos(origin);
        setDimensions(dimensions);
        setColor(ColorUtil.rgb(255,0,0));

    }

    @Override
    public void draw(Graphics context, Point containerOrigin){

        super.draw(context, containerOrigin);
        
        Point offsetPoint = new Point(0,0);

        offsetPoint.setX(getPos().getX() + containerOrigin.getX());
        offsetPoint.setY(getPos().getY() + containerOrigin.getY());

        context.setColor(getColor().getValue());

        context.drawRect
        (
            offsetPoint.getX(),
            offsetPoint.getY(),
            getDimensions().getWidth(),
            getDimensions().getHeight(),
            5
        );

    }

    public void setFireInBuilding(Fire fire){
        //do stuff
    }

}
