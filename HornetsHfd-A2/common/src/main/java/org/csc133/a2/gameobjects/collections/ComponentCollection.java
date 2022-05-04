package org.csc133.a2.gameobjects.collections;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.Component;

public class ComponentCollection extends GameObjectCollection<Component> {

    public ComponentCollection(){
        super();
        setPos(new Point(0,0));
    }


    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        if(size() > 0){

            for(Component object: gameObjects) {
                object.draw(context, object.getPos(), screenOrigin);
                //System.out.println("Attempting to draw");
            }
        }
    }
}
