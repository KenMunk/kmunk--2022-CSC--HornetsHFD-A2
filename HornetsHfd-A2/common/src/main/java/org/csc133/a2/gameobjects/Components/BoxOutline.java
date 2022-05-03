package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class BoxOutline extends ShapePrimitive{

    int thickness;

    public BoxOutline(int color, Dimension dimensions){
        super(color,dimensions);
        thickness = 1;
    }

    public void setThickness(int thickness){

    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin)
    {
        cn1ForwardPrimitiveTranslate(context,getDimensions());

        context.drawRect(0,0,getWidth(),getHeight());

        cn1ReversePrimitiveTranslate(context,getDimensions());
    }



}
