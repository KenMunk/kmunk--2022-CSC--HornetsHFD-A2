package org.csc133.a2.gameobjects.Fixed;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.BoxOutline;
import org.csc133.a2.gameobjects.Components.CircleOutline;
import org.csc133.a2.gameobjects.Components.Component;
import org.csc133.a2.gameobjects.Movables.Helicopter;

public class Helipad extends Fixed {

    public Helipad(Point position){
        this.setPos(position);
        setDimensions(new Dimension((1920/15),(1920/15)));

        Point centerPoint = new Point(0,0);

        initComponents();

        BoxOutline perimeter = new BoxOutline(ColorUtil.LTGRAY,
                getDimensions(),5);
        perimeter.setPos(centerPoint);

        CircleOutline ring = new CircleOutline(ColorUtil.LTGRAY,
                getDimensions(), 5);
        perimeter.setPos(centerPoint);

        getComponents().add(ring);
        getComponents().add(perimeter);

    }

    @Override
    public boolean containsPoint(Point checkPoint){
        return(

            this.getPos().getX() - (getWidth()/2) > checkPoint.getX()
            &&
            this.getPos().getY() - (getWidth()/2) < checkPoint.getX()

            &&

            this.getPos().getX() + (getWidth()/2) > checkPoint.getY()
            &&
            this.getPos().getY() + (getWidth()/2) < checkPoint.getY()

        );
    }

    public boolean holdsHelicopter(Helicopter aHelicopter){
        return
        (
            radiusContainsPoint(aHelicopter.getPos(),300)
            &&
            aHelicopter.isStopped()
            &&
            !aHelicopter.isPowered()
        );
    }

}
