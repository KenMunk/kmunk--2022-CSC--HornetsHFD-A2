package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class BoxOutline extends ShapeOutlinePrimitive{

    public BoxOutline(int color, Dimension dimensions){
        super(color,dimensions,1);;
    }

    public BoxOutline(int color, Dimension dimension, int thickness){
        super(color, dimension, thickness);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin)
    {

        cn1ForwardPrimitiveTranslate(context,getDimensions());

        context.drawRect(0,0,getWidth(),getHeight(),thickness);

        cn1ReversePrimitiveTranslate(context,getDimensions());
    }



}
