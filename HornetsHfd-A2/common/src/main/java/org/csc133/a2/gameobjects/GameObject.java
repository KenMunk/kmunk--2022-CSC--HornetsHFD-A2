package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.ColorInt;
import org.csc133.a2.interfaces.Drawable;

public abstract class GameObject implements Drawable {

    private Point pos;
    private Dimension dimensions;
    private ColorInt color;

    public Point getPos(){
        return(pos);
    }

    public Point setPos(Point newPos) {
        this.pos = newPos;
        return(pos);
    }

    public void setDimensions(Dimension newDimensions){
        this.dimensions = newDimensions;
    }

    public Dimension getDimensions(){
        return(dimensions);
    }

    public boolean containsPoint(Point reference){
        return
        (
            reference.getY() >= this.pos.getY()
            &&
            (
                reference.getY()
                <=
                this.pos.getY()+this.dimensions.getHeight()
            )
            &&
            reference.getX() >= this.pos.getX()
            &&
            (
                reference.getX()
                <=
                this.pos.getX()+this.dimensions.getWidth()
            )
        );
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

    }
}
