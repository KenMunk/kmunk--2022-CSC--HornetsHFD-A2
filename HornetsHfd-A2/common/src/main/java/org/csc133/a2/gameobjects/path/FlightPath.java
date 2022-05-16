package org.csc133.a2.gameobjects.path;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.GameObject;

public class FlightPath extends GameObject {
    Point origin;

    public FlightPath(Point starting, Point ending){
        origin = starting;
        //System.out.println("Line Starting at: " + origin);
        setPos(ending);
        //System.out.println("Line ending at: " + getPos());
        setDimensions(new Dimension(20,20));
        setColor(ColorUtil.GREEN);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        context.drawLine(
                origin.getX()-getPos().getX(),
                origin.getY()-getPos().getY(),
                0,
                0);
    }

}
