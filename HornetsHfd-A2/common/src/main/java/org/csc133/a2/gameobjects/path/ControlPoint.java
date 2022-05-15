package org.csc133.a2.gameobjects.path;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.Components.CircleSolid;
import org.csc133.a2.gameobjects.Components.ComponentNode;
import org.csc133.a2.gameobjects.GameObject;

public class ControlPoint extends ComponentNode {

    private final boolean showControlPoint = true;

    public ControlPoint(Point position){
        setPos(position);

        initComponents();

        if(showControlPoint){
            getComponents().add
            (
                new CircleSolid(ColorUtil.GREEN,
                new Dimension(10,10))
            );
        }
    }

    public float distanceTo(Point reference){
        float distance = 0;

        double differenceX =
                MathUtil.pow(getPos().getX() - reference.getX(),2);
        double differenceY =
                MathUtil.pow(getPos().getX() - reference.getX(),2);

        distance = (float)MathUtil.pow(differenceX+differenceY,0.5f);

        return(distance);
    }


}
