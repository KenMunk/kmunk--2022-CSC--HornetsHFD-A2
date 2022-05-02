package org.csc133.a2.gameobjects.path;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.collections.GameObjectCollection;

import java.util.ArrayList;

public class Path extends GameObjectCollection<Path> {

    public Path(){
        super();
        init(new Point(0,0));
    }

    public Path(Point location){
        super();
        init(location);
    }

    public void init(Point location){
        setPos(location);
        setColor(ColorUtil.GREEN);
    }

    @Override
    public void draw(Graphics context, Point parentOrigin,
                     Point screenOrigin){
        containerTranslate(context, parentOrigin);

        //ignore the commented out crap
        //Transform transform = Transform.makeIdentity();
        //context.getTransform(transform);

        //local transforms
        //rotateTransform(transform);
        localDraw(context, getPos(), screenOrigin);
        //undoRotateTransform(transform);


        //undo the local transforms

        undoContainerTranslate(context, parentOrigin);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        context.setColor(getColor().getValue());
        if(size() > 0){
            for(Path object: this){
                context.drawLine(0,0,object.getPos().getX(),
                        object.getPos().getY());
                object.draw(context,object.getPos(),screenOrigin);
            }
        }
    }

}
