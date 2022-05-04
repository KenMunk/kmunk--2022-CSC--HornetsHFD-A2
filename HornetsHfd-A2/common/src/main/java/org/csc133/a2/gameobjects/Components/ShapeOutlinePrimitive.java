package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.geom.Dimension;

public class ShapeOutlinePrimitive extends ShapePrimitive{

    protected int thickness;

    public ShapeOutlinePrimitive(int color, Dimension dimensions){
        super(color,dimensions);
        thickness = 1;
    }
    public ShapeOutlinePrimitive(int color, Dimension dimensions,
                                 int thickness){
        super(color,dimensions);
        setThickness(thickness);
    }

    public void setThickness(int thickness){
        this.thickness = thickness;
    }

}
