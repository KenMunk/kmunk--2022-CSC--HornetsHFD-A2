package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
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

    public Dimension getDimensions(){
        return(dimensions);
    }

}
