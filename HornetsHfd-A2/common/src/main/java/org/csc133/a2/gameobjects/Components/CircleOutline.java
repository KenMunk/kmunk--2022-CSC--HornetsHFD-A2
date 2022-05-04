package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class CircleOutline extends ShapeOutlinePrimitive{

    public CircleOutline(int color, Dimension dimensions){
        super(color,dimensions,1);;
    }

    public CircleOutline(int color, Dimension dimension,
                        int thickness){
        super(color, dimension, thickness);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin)
    {

        cn1ForwardPrimitiveTranslate(context,getDimensions());

        for(int i = 0; i < thickness; i++){
            context.drawArc(0+i,0+i,
                            getWidth()-2*i,getHeight()-2*i,
                            0,360);

        }

        cn1ReversePrimitiveTranslate(context,getDimensions());
    }

}
