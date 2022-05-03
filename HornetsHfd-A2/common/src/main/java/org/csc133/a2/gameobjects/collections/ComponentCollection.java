package org.csc133.a2.gameobjects.collections;

import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.Component;

public class ComponentCollection extends GameObjectCollection<Component> {

    public ComponentCollection(){
        super();
        setPos(new Point(0,0));
    }


}
