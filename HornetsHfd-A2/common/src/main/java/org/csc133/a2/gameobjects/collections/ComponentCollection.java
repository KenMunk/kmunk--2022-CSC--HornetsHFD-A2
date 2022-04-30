package org.csc133.a2.gameobjects.collections;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Component;

public class ComponentCollection extends GameObjectCollection<Component> {

    public ComponentCollection(){
        super();
        setPos(new Point(0,0));
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        for(Component aComponent: gameObjects){
            localDraw(context, parentOrigin, screenOrigin);
        }
    }


}
