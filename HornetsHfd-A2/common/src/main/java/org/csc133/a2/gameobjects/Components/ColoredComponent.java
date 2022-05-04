package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.geom.Point;

public class ColoredComponent extends Component{

    public ColoredComponent(int color){
        setColor(color);
        setPos(new Point(0,0));
    }

}
