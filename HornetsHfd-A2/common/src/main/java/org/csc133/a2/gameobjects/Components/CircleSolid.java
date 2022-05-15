package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class CircleSolid extends ShapePrimitive{

    public CircleSolid(int color, Dimension dimensions){
        super(color, dimensions);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin)
    {

        cn1ForwardPrimitiveTranslate(context,getDimensions());

        context.drawArc
        (
            0,0,
            getWidth(), getHeight(),
            0, 360
        );

        cn1ReversePrimitiveTranslate(context,getDimensions());
    }
}
